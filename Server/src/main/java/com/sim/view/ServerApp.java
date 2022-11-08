package com.sim.view;

import com.sim.service.ServerService;

/**
 * @className: ServerApp
 * @Package: com.sim.view

 * @description: 服务启动入口
 * @author: xiaoqi
 * @version v1.0
 * @create: 2022-11-08 21:21
 **/
public class ServerApp {
    public static void main(String[] args) {
        new ServerService();
    }
}
