package com.example.u17.module_mine.http;

import com.example.u17.module_mine.url.GameUrl;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/9
 */
public class HttpGameUtils {

    public static HttpGameService mHttpGameService;
    public static HttpGameService create(){
        if (mHttpGameService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GameUrl.URL_GAME_LIST_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mHttpGameService = retrofit.create(HttpGameService.class);
        }
        return mHttpGameService;
    }
}
