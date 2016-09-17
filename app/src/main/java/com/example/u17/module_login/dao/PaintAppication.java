package com.example.u17.module_login.dao;

import android.app.Application;



/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/17
 */
public class PaintAppication extends Application {
    /**用户信息*/

    public static PaintAppication appication;
    @Override
    public void onCreate() {
        super.onCreate();
        appication = this;
    }

}
