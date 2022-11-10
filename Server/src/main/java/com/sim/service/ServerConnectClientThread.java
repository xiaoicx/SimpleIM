package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @className: ServerConnectClientThread
 * @Package: com.sim.service

 * @description: server链接client的线程管理池
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-08 20:57
 **/
@SuppressWarnings("all")
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userId;
    private boolean loop = true;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {

        while (loop) {//循环监听客户端信息
            try {
                System.out.println("server和client保持通信 " + userId + " ,input data....");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject();

                if (msg.getSendType().equals(MessageType.MESAGE_GET_ONLINE_FRIEND)) {//获取在线用户列表
                    System.out.println("消息类型: " + msg.getSendType() + " 发送者: " + msg.getSender()
                            + "-> 客户端请求在线列表信息");

                    //列表形式: 100 200 ...
                    String onlineUsers = ManngerServerConnectClientThreads.getOnlineUsers();

                    //构造返回信息
                    Message message = new Message();
                    message.setSendType(MessageType.MESAGE_RET_ONLINE_FRIEND);
                    message.setContent(onlineUsers);
                    message.setGeter(msg.getSender());

                    //回送消息到客户端
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();


                } else if (msg.getSendType().equals(MessageType.MESAGE_CLIENT_EXIT)) {//客户端退出
                    System.out.println("消息类型: " + msg.getSendType() + " 发送者: " + msg.getSender()
                            + "-> 客户端退出信息");

                    //结束监听客户端线程
                    loop = false;
                    objectInputStream.close();
                    socket.close();
                    ManngerServerConnectClientThreads.removeServerConnectClientThread(msg.getSender());


                } else if (msg.getSendType().equals(MessageType.MESAGE_COMM_MES)) { //转发聊天消息
                    System.out.println("消息类型: " + msg.getSendType() + " 发送者: " + msg.getSender()
                            + "-> 私聊信息");

                    //获取转发用户信息
                    String msgGeter = msg.getGeter();

                    //获取接收端socket线程 且 转发消息
                    ServerConnectClientThread serverConnectClientThread = ManngerServerConnectClientThreads.getServerConnectClientThread(msgGeter);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.socket.getOutputStream());
                    objectOutputStream.writeObject(msg);
                    objectOutputStream.flush();


                } else if (msg.getSendType().equals(MessageType.MESAGE_GROUP_MES)) {//转发群聊信息
                    System.out.println("消息类型: " + msg.getSendType() + " 发送者: " + msg.getSender()
                            + "-> 群聊信息");

                    HashMap<String, ServerConnectClientThread> hashMap = ManngerServerConnectClientThreads.getHashMap();
                    Iterator<String> iterator = hashMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        //取出用户id
                        String userId = iterator.next().toString();

                        //排除发送消息的用户 后 转发
                        if (!userId.equals(msg.getSender())) {
                            ServerConnectClientThread serverConnectClientThread = ManngerServerConnectClientThreads.getServerConnectClientThread(userId);
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                            objectOutputStream.writeObject(msg);
                            objectOutputStream.flush();
                        }
                    }


                } else if (msg.getSendType().equals(MessageType.MESAGE_PRI_FILE_MES)) {//转发私聊发送文件
                    System.out.println("消息类型: " + msg.getSendType() + " 发送者: " + msg.getSender()
                            + "-> 文件信息 文件名: '" + msg.getFileName() + "' 文件大小: " + msg.getContentLength());

                    //获取转发用户信息
                    String msgGeter = msg.getGeter();

                    //获取接收端socket线程 且 转发消息
                    ServerConnectClientThread serverConnectClientThread = ManngerServerConnectClientThreads.getServerConnectClientThread(msgGeter);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    objectOutputStream.writeObject(msg);
                    objectOutputStream.flush();


                } else {
                    System.out.println("消息为其他类型......");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }

}
