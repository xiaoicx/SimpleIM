package com.sim.service;

import java.util.HashMap;

/**
 * @className: ManagerClientConnectServerThread
 * @Package: com.sim.service

 * @description: ManagerClientConnectServerThread
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-08 17:40
 **/
@SuppressWarnings("all")
public class ManagerClientConnectServerThread {
    //把thread 放入到hashmap中  key=id  value=线程
    private static HashMap<String, ClientConnectThreadService> clientConnectThreadServiceHashMap = new HashMap<>();


    /**
     * @Description 添加线程到hashmap
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-08 17:45
     * @param userId
     * @param clientConnectThreadService
     * @return
     */
    public static void addClientConnectServerThread(String userId, ClientConnectThreadService clientConnectThreadService) {
        clientConnectThreadServiceHashMap.put(userId, clientConnectThreadService);
    }

    /**
     * @Description 获取线程
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-08 17:45
     * @param UserId
     * @return
     */
    public static ClientConnectThreadService getClientConnectThreadService(String UserId) {
        return clientConnectThreadServiceHashMap.get(UserId);
    }

    /**
     * @Description 移出监听线程
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-09 17:59
     * @param userId
     * @return
     */
    public static ClientConnectThreadService removeClientConnectThreadService(String userId) {
        return clientConnectThreadServiceHashMap.remove(userId);
    }


}
