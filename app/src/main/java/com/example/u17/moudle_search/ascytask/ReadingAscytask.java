package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/13
 */
public class ReadingAscytask extends AsyncTask<String,Void,List<String>> {
    private ReadingCallBack readingCallBack;

    public ReadingAscytask(ReadingCallBack readingCallBack) {
        this.readingCallBack = readingCallBack;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        List<String> urlList=new ArrayList<>();
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
            JSONArray image_list = returnData.getJSONArray("image_list");
            int length = image_list.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = image_list.getJSONObject(i);
                String img05 = jsonObject.getString("img50");
                urlList.add(img05);
            }
            return urlList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> urls) {
        readingCallBack.callBack(urls);
    }
    public interface ReadingCallBack{
        void callBack(List<String> urls);
    }
}
