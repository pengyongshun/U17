package com.example.u17.base_ascytask;

import android.os.AsyncTask;

import com.example.u17.base_bean.StatisticsBottomBean;
import com.example.u17.base_uitls.log_uitls.LogUtils;
import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/12
 */
public class StatisticsBottomAscytask extends AsyncTask<String,Void,List<StatisticsBottomBean>> {
    private BaseSBCallBack baseSBCallBack;

    public StatisticsBottomAscytask(BaseSBCallBack baseSBCallBack) {
        this.baseSBCallBack = baseSBCallBack;
    }

    @Override
    protected List<StatisticsBottomBean> doInBackground(String... params) {
        List<StatisticsBottomBean> bottomBeanList=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(params[0]);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        try {
            JSONObject object=new JSONObject(json);
            String data1 = object.getString("data");
            JSONObject object1 = new JSONObject(data1);
            JSONArray returnData = object1.getJSONArray("returnData");
            int length = returnData.length();
            for (int i = 0; i < length; i++) {
                StatisticsBottomBean bean=new StatisticsBottomBean();
                JSONObject jsonObject = returnData.getJSONObject(i);
                String comic_id = jsonObject.getString("comic_id");
                String name = jsonObject.getString("name");
                String cover = jsonObject.getString("cover");
                bean.setName(name);
                bean.setCover(cover);
                bean.setComic_id(comic_id);
                bottomBeanList.add(bean);
            }
            return bottomBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<StatisticsBottomBean> statisticsBottomBean) {
        baseSBCallBack.callBack(statisticsBottomBean);
    }
    public interface BaseSBCallBack{
        void callBack(List<StatisticsBottomBean> statisticsBottomBean);
    }
}
