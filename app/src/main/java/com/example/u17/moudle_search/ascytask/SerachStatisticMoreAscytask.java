package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.moudle_search.bean.SerachStatisticMoreBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        SerachStatisticMoreBean bean=new SerachStatisticMoreBean();
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(params[0]);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        try {
            SerachStatisticMoreBean.ComicBean comic1 = new SerachStatisticMoreBean.ComicBean();
            JSONObject object=new JSONObject(json);
            String data = object.getString("data");
            JSONObject object1 = new JSONObject(data);
            JSONObject returnData = object1.getJSONObject("returnData");
            JSONObject comic = returnData.getJSONObject("comic");
            String cover = comic.getString("cover");
            String comic_id = comic.getString("comic_id");
            JSONObject author = comic.getJSONObject("author");
            String name = author.getString("name");
            String avatar = author.getString("avatar");
            String short_description = comic.getString("short_description");
            JSONArray theme_ids = comic.getJSONArray("theme_ids");
            int i1 = theme_ids.length();
            List<String> ids=new ArrayList<>();
            for (int i = 0; i < i1; i++) {
                String them_id = theme_ids.getString(i);
                ids.add(them_id);
            }
            String name1 = comic.getString("name");
            String description = comic.getString("description");
            String last_update_week = comic.getString("last_update_week");
            String cate_id = comic.getString("cate_id");
            comic1.setComic_id(comic_id);
            comic1.setName(name1);
            comic1.setCate_id(cate_id);
            comic1.setLast_update_week(last_update_week);
            comic1.setShort_description(short_description);
            comic1.setDescription(description);
            SerachStatisticMoreBean.ComicBean.AuthorBean author1 = new SerachStatisticMoreBean.ComicBean.AuthorBean();
            author1.setName(name);
            author1.setAvatar(avatar);
            comic1.setCover(cover);
            comic1.setTheme_ids(ids);
            comic1.setAuthor(author1);
            bean.setComic(comic1);
            //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,chapter_list
            List<SerachStatisticMoreBean.ChapterListBean> chapter_list1 = new ArrayList<>();
            JSONArray chapter_list = returnData.getJSONArray("chapter_list");
            int length = chapter_list.length();
            for (int i = 0; i < length; i++) {
                SerachStatisticMoreBean.ChapterListBean chapterListBean=new SerachStatisticMoreBean.ChapterListBean();
                JSONObject jsonObject = chapter_list.getJSONObject(i);
                String name2 = jsonObject.getString("name");
                chapterListBean.setName(name2);
                chapter_list1.add(chapterListBean);
            }
             bean.setChapter_list(chapter_list1);
            //---------------otherwork
            List<SerachStatisticMoreBean.OtherWorksBean> otherWorks1 = new ArrayList<>();

            JSONArray otherWorks = returnData.getJSONArray("otherWorks");
            int length1 = otherWorks.length();
            for (int i = 0; i < length1; i++) {
                SerachStatisticMoreBean.OtherWorksBean otherWorksBean=new SerachStatisticMoreBean.OtherWorksBean();

                JSONObject jsonObject = otherWorks.getJSONObject(i);
                String comicId = jsonObject.getString("comicId");
                String coverUrl = jsonObject.getString("coverUrl");
                String name3 = jsonObject.getString("name");
                otherWorksBean.setName(name3);
                otherWorksBean.setComicId(comicId);
                otherWorksBean.setCoverUrl(coverUrl);
                otherWorks1.add(otherWorksBean);
            }
            bean.setOtherWorks(otherWorks1);
            return bean;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(SerachStatisticMoreBean serachStatisticMoreBean) {
        ssmCallBack.callBack(serachStatisticMoreBean);
    }
    public interface SSMCallBack{
        void callBack(SerachStatisticMoreBean serachStatisticMoreBean);
    }
}
