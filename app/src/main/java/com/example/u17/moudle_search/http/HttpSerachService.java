package com.example.u17.moudle_search.http;

import com.example.u17.moudle_search.bean.SerachDetialTodayBean;
import com.example.u17.moudle_search.bean.SerachListBean;
import com.example.u17.moudle_search.url.SerachUrl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/8
 */
public interface HttpSerachService {
    //搜索界面的列表
    @GET(SerachUrl.SERACH_LIST_URL_BOTTOM)
    Call<SerachListBean> getSerachListBeanFromNet();
//    //搜索界面的头部列表的详情  今日最热
//    @GET(SerachUrl.SERACH_DETIAL_HEAD_URL_BOTTOM)
//    Call<SerachDetialTodayBean> getSerachDetialToadayBeanFromNet(@Query("argValue")int vaule,@Query("argName")String name,@Query("argCon")int con,@Query("page")int page);
}
