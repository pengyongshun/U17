package com.xinying.httplibrary;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Eline on 2016/6/1.
 */
public abstract class OkHttpTask {
    private static OkHttpClient okHttpClient;
    public static final String URL_REGEX = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";

    public static OkHttpTaskBuilder newInstance(Context context){
        if (okHttpClient ==null){
            File cacheDir = context.getCacheDir();
            okHttpClient = new OkHttpClient.Builder().cache(new Cache(cacheDir,4*1024*1024)).build();
        }
        OkHttpTaskBuilder okHttpTask = new OkHttpTaskBuilder();
        return okHttpTask;
    }

    public static  class  OkHttpTaskBuilder extends AsyncTask<String,Integer,String> {
        protected boolean isCache;
        private IOkTaskCallBack callBack;
        private boolean post;
        private RequestBody formBody;

        public enum OkType{
            form,json
        }
        public  void start(String url){
            if (url == null){
                throw new NullPointerException("url is null");
            }
            if (!url.matches(URL_REGEX)){
                throw new IllegalArgumentException("url is wrong");
            }
            this.execute(url);
        }

        public  OkHttpTaskBuilder enqueue(IOkTaskCallBack callBack){
            this.callBack = callBack;
            return  OkHttpTaskBuilder.this;
        }

        public OkHttpTaskBuilder post(Map<String,String> param, OkType type){
            if (param == null){
                throw new NullPointerException("param is null");
            }

            post = true;
            switch (type){
                case form:
                    FormBody.Builder builder = new FormBody.Builder();
                    Set<String> strings = param.keySet();
                    for (String key:strings) {
                        builder.add(key,param.get(key));
                    }
                    formBody = builder.build();
                    break;
                case json:
                    try {
                        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                        JSONObject jsonObject = new JSONObject();
                        Set<String> keSet = param.keySet();
                        for (String key:keSet) {
                            jsonObject.put(key,param.get(key));
                        }
                        formBody = RequestBody.create(mediaType,jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            return OkHttpTaskBuilder.this;
        }


        /**
         * 执行请求网络
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            Request.Builder builder = new Request.Builder();
            if (isCache){
                builder.cacheControl(CacheControl.FORCE_CACHE);
            }
            Response response = null;
            try {
                if (post){
                    builder.post(formBody);
                }
                Request request = builder.url(url).build();
                response = okHttpClient.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 接收网络返回的数据
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (callBack != null){
                callBack.onSuccess(s);
            }
        }
    }

}
