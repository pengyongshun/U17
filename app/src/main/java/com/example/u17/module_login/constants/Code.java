package com.example.u17.module_login.constants;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/16
 */
public class Code {

    /**
     * 获取注册信息
     */
    public static String getRegisterMsg(int statusCode) {
        String msg = "系统内部错误";
        switch (statusCode) {
            case 0:
                msg = "注册成功";
                break;
            case -1:
                msg = "注册失败";
                break;
            case -2:
                msg = "邮箱已被注册";
                break;
        }
        return msg;
    }

}
