package com.geniusvjr.geniusnote.model;

import android.content.Context;
import android.content.SharedPreferences;

import cn.bmob.v3.BmobUser;

/**
 * Created by dream on 16/1/30.
 */
public class User extends BmobUser{

    public static boolean isLogin = false;

    private String userSays;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserSays() {
        return userSays;
    }

    public void setUserSays(String userSays) {
        this.userSays = userSays;
    }






}
