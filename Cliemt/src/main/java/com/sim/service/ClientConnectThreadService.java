package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public ClientConnectThreadService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        //保持一个线程不听的监听服务端请求
        while (true){

            try {
                System.out.println("client thread waiting server msg......");
                ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());
                Message msg = (Message) inputStream.readObject();

                //服务端返回的在线列表信息
                if (msg.getSendType().equals(MessageType.MESAGE_RET_ONLINE_FRIEND)){
                    //
                    String[] onlineUser = msg.getContent().split(" ");
                    System.out.println("============当前在线用户列表=============");
                    for (String name : onlineUser) {
                        System.out.println("用户: " + name);
                    }
                    System.out.println("======================================");


                }else {
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
