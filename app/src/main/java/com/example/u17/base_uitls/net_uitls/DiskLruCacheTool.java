package com.example.u17.base_uitls.net_uitls;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 彭永顺 on 2016/8/14.
 */
public class DiskLruCacheTool {

    private static final String TAG = "androidxx";
    private static DiskLruCache diskLruCache;
    public static final int MAX_SIZE = 4 * 1024 * 1024;

    public static void init(Context context) {
        //开启缓存对象
        /**
         * 参数1：缓存的路径
         * 参数2：versionCode
         * 参数3：默认值1，即一个key对应一个value
         * 参数4：最大缓存空间
         */
        File cacheDir = getCacheDir(context);
        int versionCode = getVersionCode(context);

        try {
            diskLruCache = DiskLruCache.open(cacheDir,versionCode,1,MAX_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取系统的VersionCode
     * @param context
     * @return
     */
    private static int getVersionCode(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取存储目录
     * @param context
     * @return
     */
    private static File getCacheDir(Context context) {
        //首先判定外置存储是否有，如果有的话，首选外置存储
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir();
        }
        //如果没有外置，则选择内置
        return context.getCacheDir();
    }


    /**
     * 将Bitmap对象写入磁盘
     * @param key
     * @param bitmap
     */
    public static void saveBitmapDiskCache(String key, Bitmap bitmap) {
        //Editor对象用来存储数据
        /**
         * 参数一：是一个key，用来唯一标识当前的Bitmap
         * key的要求：
         * 只能是a-z或者0-9这几个字符。
         * 长度不能超过32个字母
         */
        if (key==null){
           return;
        }
        String formatKey = formatKey(key);
        OutputStream outputStream = null;
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(formatKey);
            if (editor != null) {
                outputStream = editor.newOutputStream(0);
                //将Bitmap对象写入输出流
                /**
                 * 参数1：设置图片的格式。有PNG、Jpg
                 * 参数2：图片质量，100表示无压缩存储
                 * 参数3：输出流对象
                 * 返回值：true表示写入成功，false表示写入失败
                 */
            if (outputStream==null){
                return;
            }
                if (bitmap==null){
                    return;
                }
                boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                //如果写入成功，提交本次操作
                if (compress) {
                    editor.commit(); //提交
                } else {
                    //如果失败，则撤销全部操作
                    editor.abort(); //撤销
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从磁盘中读取Bitmap对象
     * @param url
     * @return
     */
    public static Bitmap readBitmapDiskCache(String url) {
        String formatKey = formatKey(url);
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(formatKey);
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                return BitmapFactory.decodeStream(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 格式化key，使key满足a-z或者0-9，并且在32个字母以内
     * @param url
     * @return
     */
    private static String formatKey(String url) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
//                System.out.println(Integer.toHexString(Math.abs(bytes[i])));
                builder.append(Integer.toHexString(Math.abs(bytes[i])));
            }
            System.out.println(builder.toString());
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return String.valueOf(url.hashCode());
    }

    public static void flush() {
        if (diskLruCache != null) {
            try {
                diskLruCache.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
