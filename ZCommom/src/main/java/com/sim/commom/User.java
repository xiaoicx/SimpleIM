package com.sim.commom;

import java.io.Serializable;

/**
 * @className: User
 * @Package: com.sim.common

 * @description: 用户信息
 * @author: xiaoqi
 * @version v1.0
 * @create: 2021-11-07 21:40
 **/
@SuppressWarnings("all")
public class User implements Serializable {

    static final long serialVersionUID = 1290593209L;

    private String userId;
    private String passwd;

    public User() {
    }

    public User(String userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
