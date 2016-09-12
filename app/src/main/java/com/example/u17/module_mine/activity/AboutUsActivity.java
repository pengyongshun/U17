package com.example.u17.module_mine.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u17.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/11
 */
public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener{
    //返回
    @BindView(R.id.about_us_image_view_back)
    ImageView mAboutUsBack;
    //检查新版本
    @BindView(R.id.about_us_text_update)
    TextView mAboutUsUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_about_us);
        ButterKnife.bind(this);
        mAboutUsBack.setOnClickListener(this);
        mAboutUsUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.about_us_image_view_back:
                finish();
                break;
            case R.id.about_us_text_update:
                Toast.makeText(AboutUsActivity.this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
