package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.moudle_search.bean.SerachStatisticsTicketBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
        SerachStatisticsTicketBean bean=new SerachStatisticsTicketBean();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(params[0]);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        try {
            JSONObject object=new JSONObject(json);
            String data = object.getString("data");
            JSONObject object1 = new JSONObject(data);
            JSONObject returnData = object1.getJSONObject("returnData");
            JSONObject comic = returnData.getJSONObject("comic");
            String comic_id = comic.getString("comic_id");
            String month_ticket = comic.getString("month_ticket");
            int total_ticket = comic.getInt("total_ticket");
            int total_tucao = comic.getInt("total_tucao");
            int favorite_total = comic.getInt("favorite_total");
            int total_click = comic.getInt("total_click");
            JSONObject comment = returnData.getJSONObject("comment");
            String commentCount = comment.getString("commentCount");
            bean.setComic_id(comic_id);
            bean.setCommentCount(commentCount);
            bean.setFavorite_total(favorite_total);
            bean.setMonth_ticket(month_ticket);
            bean.setTotal_click(total_click);
            bean.setTotal_ticket(total_ticket);
            bean.setTotal_tucao(total_tucao);
            return bean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(SerachStatisticsTicketBean serachStatisticsTicketBean) {
        ssCallBack.callBack(serachStatisticsTicketBean);
    }
    public interface SSCallBack{
        void callBack(SerachStatisticsTicketBean serachStatisticsTicketBean);
    }
}
