package com.example.u17.moudle_search.http;

import com.example.u17.module_home.http.HttpHomeListService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/8
 */
public class HttpSerachUitls {
    private static HttpSerachService httpService;
    public static HttpSerachService init(String baseUrl){
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
            httpService = retrofit.create(HttpSerachService.class);

        }
        return httpService;
    }
}
