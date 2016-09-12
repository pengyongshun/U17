package com.example.u17.base_ascytask;

import android.os.AsyncTask;

import com.example.u17.base_bean.StatisticsBottomBean;
import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.google.gson.Gson;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/12
 */
public class StatisticsBottomAscytask extends AsyncTask<String,Void,StatisticsBottomBean> {
    private BaseSBCallBack baseSBCallBack;

    public StatisticsBottomAscytask(BaseSBCallBack baseSBCallBack) {
        this.baseSBCallBack = baseSBCallBack;
    }

    @Override
    protected StatisticsBottomBean doInBackground(String... params) {
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(params[0]);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        Gson gson=new Gson();
        StatisticsBottomBean statisticsBottomBean = gson.fromJson(json, StatisticsBottomBean.class);
        return statisticsBottomBean;
    }

    @Override
    protected void onPostExecute(StatisticsBottomBean statisticsBottomBean) {
        baseSBCallBack.callBack(statisticsBottomBean);
    }
    public interface BaseSBCallBack{
        void callBack(StatisticsBottomBean statisticsBottomBean);
    }
}
