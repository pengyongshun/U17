package com.example.u17.module_mine.activity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.u17.R;
import com.example.u17.module_mine.url.AuthorUrl;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/11
 */
public class AuthorActivity extends AppCompatActivity implements View.OnClickListener{
    //返回
    @BindView(R.id.author_image_view_back)
    ImageView mAuthorBack;
    //WebView
    @BindView(R.id.author_web_view)
    WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_author);
        ButterKnife.bind(this);
        mAuthorBack.setOnClickListener(this);
        mWebView.loadUrl(AuthorUrl.AUTHOR_URL);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.author_image_view_back:
                finish();
                break;
            default:
                break;
        }
    }
}
