package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;
import com.sim.commom.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: Server
 * @Package: com.sim.service

 * @description: 服务端
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-08 17:53
 **/
@SuppressWarnings("all")
public class ServerService {

    private ServerSocket serverSocket = null;
//    private static HashMap<String, User> validUsers = new HashMap<>();
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();

    static {
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("岳不群", new User("岳不群", "123456"));
        validUsers.put("东方不败", new User("东方不败", "123456"));
        validUsers.put("令狐冲", new User("令狐冲", "123456"));
    }

    public ServerService() {
        try {
            System.out.println("listener port 9999....");
            this.serverSocket = new ServerSocket(9999);
            while (true) {
                //接收客户端信息
                Socket accept = this.serverSocket.accept();

                //获取accpet输入输出流
                ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());


                //获取客户端发送的信息
                User user = (User) objectInputStream.readObject();

                Message msg = new Message();

                if (checkUser(user.getUserId(), user.getPasswd())) {//检测用户登录
                    System.out.println("User id= " + user.getUserId() + " pwd= " + user.getPasswd() + " login success....");

                    //回送登录成功信息
                    msg.setSendType(MessageType.MESAGE_LOGIN_SUCCEED);
                    objectOutputStream.writeObject(msg);
                    objectOutputStream.flush();

                    //创建一个线程 和客户端保持通讯
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(accept, user.getUserId());
                    serverConnectClientThread.start();

                    //添加线程到list
                    ManngerServerConnectClientThreads.addClientThread(user.getUserId(), serverConnectClientThread);

                } else {//登录失败

                    System.out.println("User id= " + user.getUserId() + " pwd= " + user.getPasswd() + " login fail....");

                    //回送登录失败信息
                    msg.setSendType(MessageType.MESAGE_LOGIN_FAIL);
                    objectOutputStream.writeObject(msg);
                    objectOutputStream.flush();

                    //关闭socket
                    accept.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //服务端退出 关闭资源
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @Description 验证用户
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-08 21:59
     * @param userId
     * @param pwd
     * @return
     */
    private boolean checkUser(String userId, String pwd) {

        User user = validUsers.get(userId);
        if (user == null) {
            return false;
        }

        if ("null".equals(user.getUserId()) || "null".equals(user.getPasswd()) ||
                "null".equals(userId) || "null".equals(pwd)) {
            return false;
        }

        if (!pwd.equals(user.getPasswd())) {
            return false;
        }

        return true;
    }
}
