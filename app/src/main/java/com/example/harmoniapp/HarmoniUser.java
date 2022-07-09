package com.example.harmoniapp;

public class HarmoniUser {
    private String uId;
    private String userName;
    private String email;
    private String password;

    public HarmoniUser(String uId,String userName, String email) {
        this.uId = uId;
        this.userName = userName;
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
