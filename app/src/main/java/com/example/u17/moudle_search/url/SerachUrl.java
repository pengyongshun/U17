package com.example.u17.moudle_search.url;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/8
 */
public class SerachUrl {
    //搜索界面的首页
    public static final String SERACH_LIST_URL="http://app.u17.com/v3/appV3/android/phone/sort/mobileCateList?v=3120100&key=bc51c447bda6fca8ed6c1fca7f707c96aa9461ecd83ebe8da8dbe7e1cc0973e2889e11972ffb439739ea78e65a2cc6ebd4ceefee0ec3e902f228a807f6be94e831632e56961f7e95512c0e4bc0c17748105837a8ccb8eb173c5e60dc400f7bdf%253A%253A%253Aopen&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b&version=2";
    public static final String SERACH_LIST_URL_BASE="http://app.u17.com";
    public static final String SERACH_LIST_URL_BOTTOM="/v3/appV3/android/phone/sort/mobileCateList?v=3120100&key=bc51c447bda6fca8ed6c1fca7f707c96aa9461ecd83ebe8da8dbe7e1cc0973e2889e11972ffb439739ea78e65a2cc6ebd4ceefee0ec3e902f228a807f6be94e831632e56961f7e95512c0e4bc0c17748105837a8ccb8eb173c5e60dc400f7bdf%253A%253A%253Aopen&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b&version=2";
    //搜索界面的head的item的详情 今日最热 根据argValue、argName、argCon、page   根据head的list部分中获取
    public static final String SERACH_DETIAL_HEAD_URL="http://app.u17.com/v3/appV3/android/phone/list/commonComicList?argValue=1&argName=serial&argCon=4&page=1&v=3120100&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    public static final String SERACH_DETIAL_HEAD_URL_BASE="http://app.u17.com/v3/appV3/android/phone/list/commonComicList";
    public static final String SERACH_DETIAL_HEAD_URL_BOTTOM="&v=3120100&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    //搜索界面的bottom的item的详情  点击1 更新2 收藏3  根据argCon；每一个item根据只需要根据argValue=121  argName=special  argCon=1  page=1
    public static final String SERACH_DETIAL_BOTTOM_URL="http://app.u17.com/v3/appV3/android/phone/list/commonComicList?argValue=121&argName=special&argCon=1&page=1&v=3120100&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    public static final String SERACH_DETIAL_BOTTOM_URL_BASE="http://app.u17.com/v3/appV3/android/phone/list/commonComicList";
    public static final String SERACH_DETIAL_BOTTOM_URL_BOTTOM="&v=3120100&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
    //所有节目目录  根据chapter_id=527470
    public static final String AllCATE_Item_URL="http://app.u17.com/v3/appV3/android/phone/comic/chapterNew?v=3120100&chapter_id=527470&model=KIW-TL00H&come_from=HUAWEI&android_id=d89014eb2314698b";
}
