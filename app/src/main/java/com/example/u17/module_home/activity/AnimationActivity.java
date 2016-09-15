package com.example.u17.module_home.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.u17.JsInterface.HtmlJsInterface;
import com.example.u17.R;
import com.example.u17.module_home.url.HomeUrl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {
    @BindView(R.id.web_view_animation)
    WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        //设置支持javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        //启动缓存
        mWebView.getSettings().setAppCacheEnabled(true);
        //设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //加载网页
        mWebView.loadUrl(HomeUrl.ANIMATION_PATH);
        //在当前的浏览器中响应
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.addJavascriptInterface(new HtmlJsInterface(this),"jsObj");
    }

    @OnClick(R.id.iv_animation_back)
    public void click(View view){
        onBackPressed();
    }
    }

