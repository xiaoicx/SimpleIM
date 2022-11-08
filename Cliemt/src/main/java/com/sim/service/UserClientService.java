package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;
import com.sim.commom.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @className: UserClientService
 * @Package: com.sim.service

 * @description: 用户注册登录验证
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-08 17:07
 **/
@SuppressWarnings("all")
public class UserClientService {


    private User user = new User();


    /**
     * @Description 验证用户
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-08 17:09
     * @param userId
     * @param pwd
     * @return
     */
    public boolean checkUser(String userId, String pwd) {

        boolean f = false;

        user.setUserId(userId);
        user.setPasswd(pwd);

        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);

            //send data
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();

            //recver data
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message msg = (Message) objectInputStream.readObject();

            //login status
            if (msg.getSendType().equals(MessageType.MESAGE_LOGIN_SUCCEED)) {//登录成功执行

                //create listener thread and start
                ClientConnectThreadService clientConnectThreadService = new ClientConnectThreadService(socket);
                clientConnectThreadService.start();

                //add thread to list
                ManagerClientConnectServerThread.addClientConnectServerThread(userId, clientConnectThreadService);

                f = true;

            } else {
//                System.out.println("User id= " + user.getUserId() + "pwd= " + user.getPasswd()+"login fail");
                f = false;
                //没有登录成功  关闭socket
                socket.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return f;
    }

    /**
     * @Description 获取服务端在线列表
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-08 22:24
     * @param
     * @return
     */
    public void onLineFriendList() {

        //MESAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setSendType(MessageType.MESAGE_GET_ONLINE_FRIEND);
        message.setSender(user.getUserId());

        try {
            //从线程管理池取出当前用户的线程
            ClientConnectThreadService clientConnectThreadService = ManagerClientConnectServerThread.getClientConnectThreadService(user.getUserId());
            Socket socket = clientConnectThreadService.getSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            //发送请求到服务器获取在线列表
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }


}
