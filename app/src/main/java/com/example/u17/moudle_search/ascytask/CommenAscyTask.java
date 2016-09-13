package com.example.u17.moudle_search.ascytask;

import android.os.AsyncTask;

import com.example.u17.base_uitls.net_uitls.HttpURLUtils;
import com.example.u17.moudle_search.bean.CommentBean;
import com.google.gson.Gson;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/13
 */
public class CommenAscyTask extends AsyncTask<String,Void,CommentBean> {
    private CommenCallBack commenCallBack;

    public CommenAscyTask(CommenCallBack commenCallBack) {
        this.commenCallBack = commenCallBack;
    }

    @Override
    protected CommentBean doInBackground(String... params) {
        byte[] bytes = HttpURLUtils.getBytesFromNetWorkGetMethod(params[0]);
        String json=new String(bytes,0,bytes.length);
        if (json==null){
            return null;
        }
        Gson gson=new Gson();
        CommentBean commentBean = gson.fromJson(json, CommentBean.class);
        return commentBean;
    }

    @Override
    protected void onPostExecute(CommentBean commentBean) {
        commenCallBack.callBack(commentBean);
    }
    public interface CommenCallBack{
        void callBack(CommentBean commentBean);
    }
}
