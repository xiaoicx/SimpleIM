package com.sim.commom;

import java.io.Serializable;

/**
 * @className: Message
 * @Package: com.sim.common

 * @description: 消息信息对象
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-07 21:41
 **/
@SuppressWarnings("all")
public class Message implements Serializable {

    static final long serialVersionUID = 4215644342L;

    private String sender;
    private String geter;
    private String content;
    private String sendTime;
    private String sendType;


    public Message() {
    }

    public Message(String sender, String geter, String content, String sendTime) {
        this.sender = sender;
        this.geter = geter;
        this.content = content;
        this.sendTime = sendTime;
    }

    public Message(String sender, String geter, String content, String sendTime, String sendType) {
        this.sender = sender;
        this.geter = geter;
        this.content = content;
        this.sendTime = sendTime;
        this.sendType = sendType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGeter() {
        return geter;
    }

    public void setGeter(String geter) {
        this.geter = geter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
}
