package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
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

                } else {
                    System.out.println("消息为其他类型......");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
