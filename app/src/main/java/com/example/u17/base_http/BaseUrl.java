package com.example.u17.base_http;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/10
 */
public class BaseUrl {
    //(2)例如像serach界面的头部的item的item布局中More，统计数据的界面  根据你上个界面（1）的comicid=132814来的
    public static final String STATISTICS_BASE_MORE_URL="http://app.u17.com/v3/appV3/android/phone/comic/detail_static_new?v=3120100&comicid=";
    public static final String STATISTICS_BOTTOM_MORE_URL="&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    public static final String STATISTICS_MORE_URL="http://app.u17.com/v3/appV3/android/phone/comic/detail_static_new?v=3120100&comicid=13707&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    //(1)例如像serach界面的头部的item的item布局,统计票数  根据你上个界面(对应的列表的item的)的comicid=132814来的
    public static final String STATISTICS_URL="http://app.u17.com/v3/appV3/android/phone/comic/detail_realtime?v=3120100&t=1473263273&comicid=13707&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    public static final String STATISTICS_BASE_URL="http://app.u17.com/v3/appV3/android/phone/comic/detail_realtime?v=3120100&t=1473263273&comicid=";
    public static final String STATISTICS_BOTTOM_URL="&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    //猜你喜欢：
    public static final String STATISTICS_LIKE_URL="http://app.u17.com/v3/appV3/android/phone/comic/guessLike?v=3120100&device_id=867922023666636&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
}
