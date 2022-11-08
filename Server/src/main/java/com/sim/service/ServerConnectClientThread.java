package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;
import com.sim.commom.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

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

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {

        while (true) {//循环监听客户端信息
            try {
                System.out.println("server和client保持通信 " + userId + " ,input data....");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject();

                if (msg.getSendType().equals(MessageType.MESAGE_GET_ONLINE_FRIEND)) {
                    System.out.println("消息类型: " + msg.getSendType() + "发送者: " + msg.getSender()
                            + "->客户端请求在线列表信息");

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
