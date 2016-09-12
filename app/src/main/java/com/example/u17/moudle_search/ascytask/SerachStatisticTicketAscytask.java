package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.moudle_search.bean.SerachStatisticsTicketBean;
import com.google.gson.Gson;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/10
 */
public class SerachStatisticTicketAscytask extends AsyncTask<String,Void,SerachStatisticsTicketBean> {
    private SSCallBack ssCallBack;

    public SerachStatisticTicketAscytask(SSCallBack ssCallBack) {
        this.ssCallBack = ssCallBack;
    }

    @Override
    protected SerachStatisticsTicketBean doInBackground(String... params) {
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(params[0]);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        Gson gson=new Gson();
        SerachStatisticsTicketBean serachStatisticsTicketBean = gson.fromJson(json, SerachStatisticsTicketBean.class);
        return serachStatisticsTicketBean;
    }

    @Override
    protected void onPostExecute(SerachStatisticsTicketBean serachStatisticsTicketBean) {
        ssCallBack.callBack(serachStatisticsTicketBean);
    }
    public interface SSCallBack{
        void callBack(SerachStatisticsTicketBean serachStatisticsTicketBean);
    }
}
