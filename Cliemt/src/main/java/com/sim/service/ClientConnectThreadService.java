package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * @className: ClientConnectThreadService
 * @Package: com.sim.service

 * @description: 客户端通讯线程
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-08 17:30
 **/
@SuppressWarnings("all")
public class ClientConnectThreadService extends Thread {

    private Socket socket;
    private boolean flag = true;

    public ClientConnectThreadService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        BufferedOutputStream bufferedOutputStream = null;

        //保持一个线程不听的监听服务端请求
        while (flag) {
            try {
                System.out.println("client thread waiting server msg......");
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) inputStream.readObject();

                //服务端返回的在线列表信息
                if (msg.getSendType().equals(MessageType.MESAGE_RET_ONLINE_FRIEND)) {//服务端返回的在线列表

                    String[] onlineUser = msg.getContent().split(" ");
                    System.out.println("============当前在线用户列表=============");
                    for (String name : onlineUser) {
                        System.out.println("用户: " + name);
                    }
                    System.out.println("======================================");

                } else if (msg.getSendType().equals(MessageType.MESAGE_COMM_MES)) {//接受转发的消息

                    System.out.println("========私聊信息============");
                    System.out.println("时间: [" + msg.getSendTime() + "] " + msg.getSender() + " 私聊 " + msg.getGeter() + "说: " + msg.getContent());
                    System.out.println("===========================");


                } else if (msg.getSendType().equals(MessageType.MESAGE_GROUP_MES)) {//接收群消息

                    System.out.println("========群聊信息============");
                    System.out.println("时间: [" + msg.getSendTime() + "] " + msg.getSender() + " 群聊说: " + msg.getContent());
                    System.out.println("===========================");

                } else if (msg.getSendType().equals(MessageType.MESAGE_PRI_FILE_MES)) {//接收私发的文件
                    System.out.println("========私聊信息(文件)=======");

                    //判断文件是否存在 不存在创建
                    File file = new File("C:/" + msg.getGeter());
                    if (!file.exists()) {
                        file.mkdirs();
                    }

                    //接收文件
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file + "/" + msg.getFileName()));
                    bufferedOutputStream.write(msg.getFileBytes());

                    System.out.println("时间: [" + msg.getSendTime() + "] " + msg.getSender() + " 私聊 " + msg.getGeter() + " 接收文件: " + file + "\\" + msg.getFileName());
                    System.out.println("===========================");

                } else if (msg.getSendType().equals(MessageType.MESAGE_PUSH_NEWS)) {//新闻推送

                    String[] content = msg.getContent().split("000000-00001");
                    System.out.println("========新闻推送(标题:" + content[0] + ")=======");
                    System.out.println("时间: [" + msg.getSendTime() + "] 新闻内容: " + content[1]);
                    System.out.println("================================================");

                } else {
                    System.out.println("消息为其他类型......");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
