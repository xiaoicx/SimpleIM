package com.sim.commom;

/**
 * @className: MessageType
 * @Package: com.sim.common

 * @description: 消息类型
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-07 21:49
 **/
@SuppressWarnings("all")
public interface MessageType {

    //登录成功
    String MESAGE_LOGIN_SUCCEED = "1";

    //登录失败
    String MESAGE_LOGIN_FAIL = "2";

    //普通信息
    String MESAGE_COMM_MES = "3";

    //获取在线用户列表
    String MESAGE_GET_ONLINE_FRIEND = "4";

    //返回在线用户列表
    String MESAGE_RET_ONLINE_FRIEND = "5";

    //客户端请求退出
    String MESAGE_CLIENT_EXIT = "6";

    //群发消息
    String MESAGE_GROUP_MES = "7";

    //私聊发送文件
    String MESAGE_PRI_FILE_MES = "8";

    //群聊发送文件
    String MESAGE_PUSH_NEWS = "9";
}
