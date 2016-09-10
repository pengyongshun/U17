package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.moudle_search.bean.SerachDetialTodayBean;
import com.google.gson.Gson;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/9
 */
public class SerachDetialHeadTodayAscytask extends AsyncTask<String ,Void,SerachDetialTodayBean> {
    private SDHTCallBack mSdhtCallBack;

    public SerachDetialHeadTodayAscytask(SDHTCallBack mSdhtCallBack) {
        this.mSdhtCallBack = mSdhtCallBack;
    }

    @Override
    protected SerachDetialTodayBean doInBackground(String... params) {
        SerachDetialTodayBean bean=getBeanFromNet(params[0]);
        return bean;
    }

    private SerachDetialTodayBean getBeanFromNet(String url) {
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(url);
        String json=new String(bytes,0,bytes.length);
        if (json.isEmpty()){
            return null;
        }
        Gson gson=new Gson();
        SerachDetialTodayBean bean = gson.fromJson(json, SerachDetialTodayBean.class);
        return bean;
    }

    @Override
    protected void onPostExecute(SerachDetialTodayBean serachDetialTodayBean) {
        mSdhtCallBack.callBack(serachDetialTodayBean);
    }
    public interface SDHTCallBack{
        public void callBack(SerachDetialTodayBean serachDetialTodayBean);
    }
}
