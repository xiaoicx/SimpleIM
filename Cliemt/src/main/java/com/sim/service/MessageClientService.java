package com.sim.service;

import com.sim.Utils.Utility;
import com.sim.commom.Message;
import com.sim.commom.MessageType;

import java.io.*;


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
        message.setSendTime(Utility.getDateTimeFormat());

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
        message.setSendTime(Utility.getDateTimeFormat());

        System.out.println(senderId + "  群聊说: " + content);

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManagerClientConnectServerThread.getClientConnectThreadService(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 发送私聊文件
     * @Author xiaoqi
     * @Email onxiaoqi@qq.com
     * @Date 2021-11-10 15:48
     * @param senderId
     * @param getterId
     * @param path
     * @return
     */
    public void sendPrivateFile(String senderId, String getterId, String path) {

        //设置消息标志
        Message message = new Message();
        message.setSendType(MessageType.MESAGE_PRI_FILE_MES);
        message.setSender(senderId);
        message.setGeter(getterId);
        message.setSendTime(Utility.getDateTimeFormat());

        File file = new File(path);
        message.setFileName(file.getName());


        BufferedInputStream bufferedInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byteArrayOutputStream = new ByteArrayOutputStream();

            //读取本地文件到byteArrayOutputStream
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = bufferedInputStream.read(buf, 0, buf.length)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
                byteArrayOutputStream.flush();
            }

            //设置发送文件数据
            message.setFileBytes(byteArrayOutputStream.toByteArray());

            System.out.println(senderId + "  私聊 " + getterId + " 发送文件: " + file.getName());

            //发送文件
            ClientConnectThreadService clientConnectThreadService = ManagerClientConnectServerThread.getClientConnectThreadService(senderId);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientConnectThreadService.getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
