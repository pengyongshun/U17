package com.example.u17.module_login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.u17.R;

import butterknife.BindView;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/9
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //返回图标
    @BindView(R.id.icon_login_back)
    ImageView mLoginBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_login_back:
                //返回上一个界面
                finish();
                break;
            default:
                break;

        }
    }
}
