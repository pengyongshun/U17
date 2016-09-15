package com.example.u17.JsInterface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

import com.example.u17.moudle_search.activity.StatisticsActivity;

/**
 * Created by Eline on 2016/6/18.
 */
public class HtmlJsInterface {
    private Context context;

    public HtmlJsInterface(Context context) {
        this.context = context;
    }
    @JavascriptInterface
    public void startComicDetail(int id) {
        Intent intent = new Intent(context, StatisticsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("comicId",id);
        intent.putExtra("bundle",bundle);
        context.startActivity(intent);
    }
}
