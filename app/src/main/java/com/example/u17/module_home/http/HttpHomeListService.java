package com.example.u17.module_home.http;

import com.example.u17.module_home.bean.HomeListBean;
import com.example.u17.module_home.url.HomeUrl;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/8
 */
public interface HttpHomeListService {
    @GET(HomeUrl.HOME_LIST_URL)
    Call<HomeListBean> getHomeListDataFromNet();
}
