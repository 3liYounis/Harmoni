package com.example.harmoniapp;

import android.graphics.Bitmap;
import android.net.Uri;

public class HarmoniUser {
    private String uId;
    private String userName;
    private String email;
    private String password;
    private String profilePic;
    private int coins;
    private int activitiesDone;

    public HarmoniUser(String uId,String userName, String email,String password,String profilePic,int coins,int activitiesDone) {
        this.uId = uId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.coins = coins;
        this.activitiesDone = activitiesDone;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getActivitiesDone() {
        return activitiesDone;
    }

    public void setActivitiesDone(int activitiesDone) {
        this.activitiesDone = activitiesDone;
    }
}
