package com.example.u17.module_mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/17
 */
public class SignActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.common_image_view_back)
    ImageView mCommonBack;
    @BindView(R.id.commom_text_title)
    TextView mCommonTitle;
    @BindView(R.id.common_text_null)
    TextView mCommonNull;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2_common);
        ButterKnife.bind(this);
        mCommonTitle.setText("签到");
        mCommonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_image_view_back:
                finish();
                break;
            default:
                break;
        }
    }
}
