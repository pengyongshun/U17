package com.example.u17.base_uitls.log_uitls;

import android.util.Log;

/**
 * Created by 彭永顺 on 2016/8/14.
 */
public class LogUtils {
    private static boolean debug=true;
    public static final String TAG="tag";
    public static void log(Class clazz,String log){
            if (debug){
                Log.d(TAG, clazz.toString()+"<==============>"+log);
            }
    }
}
