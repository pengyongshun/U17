package com.example.u17.module_home.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author:pengyongshun
 * @Desc:  使用Retrofit来加载数据
 * @Time:2016/9/8
 */
public class HttpHomeListUitls {

    private static HttpHomeListService httpService;
    public static HttpHomeListService init(String baseUrl){
        if (httpService==null){
            Retrofit.Builder builder=new Retrofit.Builder();
            if (baseUrl==null){
                try {
                    throw new Exception("基础地址不能为空");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //添加基础地址
            builder.baseUrl(baseUrl);
            //添加Gson对象
            builder.addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            httpService = retrofit.create(HttpHomeListService.class);

        }
        return httpService;
    }
}
