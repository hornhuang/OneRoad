package com.example.oneroad.users;

import com.google.gson.Gson;

public class Guest {

    private String nickName;
    private String phone;
    private String password;
    private String avatar;

    public Guest(String nickName, String phone, String password, String avatar) {
        this.nickName = nickName;
        this.phone = phone;
        this.password = password;
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
