package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.moudle_search.bean.SerachStatisticMoreBean;
import com.google.gson.Gson;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/10
 */
public class SerachStatisticMoreAscytask extends AsyncTask<String,Void,SerachStatisticMoreBean> {
    private SSMCallBack ssmCallBack;

    public SerachStatisticMoreAscytask(SSMCallBack ssmCallBack) {
        this.ssmCallBack = ssmCallBack;
    }

    @Override
    protected SerachStatisticMoreBean doInBackground(String... params) {
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(params[0]);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        Gson gson=new Gson();
        SerachStatisticMoreBean serachStatisticMoreBean = gson.fromJson(json, SerachStatisticMoreBean.class);
        return serachStatisticMoreBean;
    }

    @Override
    protected void onPostExecute(SerachStatisticMoreBean serachStatisticMoreBean) {
        ssmCallBack.callBack(serachStatisticMoreBean);
    }
    public interface SSMCallBack{
        void callBack(SerachStatisticMoreBean serachStatisticMoreBean);
    }
}
