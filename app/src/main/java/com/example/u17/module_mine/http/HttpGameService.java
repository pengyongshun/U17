package com.example.u17.module_mine.http;

import com.example.u17.module_mine.bean.GameListBean;
import com.example.u17.module_mine.url.GameUrl;
import com.example.u17.moudle_search.url.SerachUrl;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @Author:king1033
 * @Desc:RxJava与Retrofit2的使用
 * @Time:2016/9/9
 */
public interface HttpGameService {
    @GET(GameUrl.URL_GAME_LIST_BASE_TAIL)
    Observable<GameListBean> queryGameServer();
}
