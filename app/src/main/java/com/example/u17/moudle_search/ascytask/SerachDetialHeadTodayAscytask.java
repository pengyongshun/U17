package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.moudle_search.bean.SerachDetialTodayBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/9
 */
public class SerachDetialHeadTodayAscytask extends AsyncTask<String ,Void,List<SerachDetialTodayBean>> {
    private SDHTCallBack mSdhtCallBack;

    public SerachDetialHeadTodayAscytask(SDHTCallBack mSdhtCallBack) {
        this.mSdhtCallBack = mSdhtCallBack;
    }

    @Override
    protected List<SerachDetialTodayBean> doInBackground(String... params) {
        List<SerachDetialTodayBean> bean=getBeanFromNet(params[0]);
        return bean;
    }

    private List<SerachDetialTodayBean> getBeanFromNet(String url) {
        List<SerachDetialTodayBean> beanList=new ArrayList<>();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(url);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        try {
            JSONObject object=new JSONObject(json);
            String data = object.getString("data");
            JSONObject jsonObject1=new JSONObject(data);
            JSONObject returnData = jsonObject1.getJSONObject("returnData");
            JSONArray comics = returnData.getJSONArray("comics");
            int length = comics.length();
            for (int i = 0; i < length; i++) {
                SerachDetialTodayBean bean=new SerachDetialTodayBean();
                List<String> list=new ArrayList<>();
                JSONObject jsonObject = comics.getJSONObject(i);
                int comicId = jsonObject.getInt("comicId");
                String cover = jsonObject.getString("cover");
                String description = jsonObject.getString("description");
                String name = jsonObject.getString("name");
                int flag = jsonObject.getInt("flag");
                String author = jsonObject.getString("author");
                String conTag = jsonObject.getString("conTag");
                JSONArray tags = jsonObject.getJSONArray("tags");
                int length1 = tags.length();
                bean.setAuthor(author);
                bean.setComicId(comicId);
                bean.setConTag(conTag);
                bean.setCover(cover);
                bean.setDescription(description);
                bean.setName(name);
                bean.setFlag(flag);
                for (int j = 0; j < length1; j++) {
                    String tag = tags.getString(j);
                    list.add(tag);
                }
                bean.setTags(list);
                beanList.add(bean);
            }
            return beanList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute( List<SerachDetialTodayBean> beans) {
        mSdhtCallBack.callBack(beans);
    }
    public interface SDHTCallBack{
        public void callBack( List<SerachDetialTodayBean> beans);
    }
}
