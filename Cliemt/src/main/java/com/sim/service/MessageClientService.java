package com.sim.service;

import com.sim.commom.Message;
import com.sim.commom.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @className: MessageClientService
 * @Package: com.sim.service

 * @description: 消息相关服务
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-09 19:23
 **/
@SuppressWarnings("all")
public class MessageClientService {

    /**
     * @Description 发送私聊信息
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-09 19:24
     * @param content
     * @param senderId
     * @param getterId
     * @return
     */
    public void sendMsgToOne(String content, String senderId, String getterId) {
        Message message = new Message();
        message.setSender(senderId);
        message.setGeter(getterId);
        message.setContent(content);
        message.setSendType(MessageType.MESAGE_COMM_MES);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Y-M-d hh:mm:ss");
        String date = simpleDateFormat.format(new Date());
        message.setSendTime(date);

        System.out.println(senderId + "  私聊 " + getterId + " 说: " + content);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManagerClientConnectServerThread.getClientConnectThreadService(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 发送群聊信息
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-09 23:45
     * @param content
     * @param senderId
     * @param getterId
     * @return
     */
    public void sendGroupMsg(String content, String senderId) {
        Message message = new Message();
        message.setSender(senderId);
        message.setSendType(MessageType.MESAGE_GROUP_MES);
        message.setContent(content);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("Y-M-d HH:mm:ss");
        String date = dateTimeFormatter.format(LocalDateTime.now());
        message.setSendTime(date);

        System.out.println(senderId + "  群聊说: " + content);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManagerClientConnectServerThread.getClientConnectThreadService(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
