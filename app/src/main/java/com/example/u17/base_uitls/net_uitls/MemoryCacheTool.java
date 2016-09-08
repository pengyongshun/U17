package com.example.u17.base_uitls.net_uitls;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by yangjw on 2016/8/5.
 */
public class MemoryCacheTool {
    public static final int MAX_SIZE = 4 * 1024 * 1024;
    private static LruCache<String,Bitmap> mLruCache = new LruCache<String,Bitmap>(MAX_SIZE) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };

    //存入图片
    public static void saveMemoryCache(String key,Bitmap value) {
        mLruCache.put(key,value);
    }

    //读取图片
    public static Bitmap readMemoryCahche(String key){
        return mLruCache.get(key);
    }
}
