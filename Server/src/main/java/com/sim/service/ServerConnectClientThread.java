package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.User;

import java.io.IOException;
import java.io.ObjectInputStream;
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
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {

        while (true){//循环监听客户端信息
            try {
                System.out.println("server和client保持通信 "+userId+" ,input data....");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message msg = (Message) objectInputStream.readObject();



            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
            }
        }
    }
}
