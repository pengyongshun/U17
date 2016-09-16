package com.example.u17.module_login.dao;


import com.example.u17.base_uitls.EncryptionUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/16
 */
public class AppDao {

    private static final String FromType = "2";

    private static AppDao instance;

    public static AppDao getInstance() {
        if (instance == null) {
            instance = new AppDao();
        }
        return instance;
    }

    private AppDao() {
    }

    public Map<String,String> createMap()
    {
        Map<String,String> map = new HashMap<>();
        return map;
    }


    /**
     * 注册
     * @param email
     * @param pass
     * @param listener
     */
//    public void userRegister(String email,String pass,CallbackListener<RegisterModel> listener)
//    {
//        Map<String,String> map = createMap();
//        map.put("Email", email);
//        map.put("Password", EncryptionUtils.createMd5(pass));
//        map.put("FromType",FromType);
//        Http.post(HttpUrl.URL_USER_REGISTER,map,listener);
//    }

    /**
     * 登录
     * @param email
     * @param pass
     * @param listener
     */
    public void userLogin(String email,String pass,CallbackListener<LoginModel> listener)
    {
        Map<String,String> map = createMap();
        map.put("Email", email);
        map.put("Password", EncryptionUtils.createMd5(pass));
        map.put("FromType",FromType);
        //Http.post(HttpUrl.URL_USER_LOGIN,map,listener);
    }



}
