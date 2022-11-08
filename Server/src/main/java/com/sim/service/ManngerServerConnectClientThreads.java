package com.sim.service;

import java.util.HashMap;

/**
 * @className: ManngerServerConnectClientThreads
 * @Package: com.sim.service

 * @description:管理客户端线程
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-08 21:08
 **/
@SuppressWarnings("all")
public class ManngerServerConnectClientThreads {
    //把thread 放入到hashmap中  key=id  value=线程
    private static HashMap<String, ServerConnectClientThread> serverConnectClientThreadHashMap = new HashMap<>();

    /**
     * @Description 添加线程到hashmap中管理
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-08 21:11
     * @param userId
     * @param serverConnectClientThread
     * @return
     */
    public static void addClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        serverConnectClientThreadHashMap.put(userId, serverConnectClientThread);
    }


    /**
     * @Description 获取hashmap中管理的线程
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-08 21:13
     * @param userId
     * @return
     */
    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return serverConnectClientThreadHashMap.get(userId);
    }

}
