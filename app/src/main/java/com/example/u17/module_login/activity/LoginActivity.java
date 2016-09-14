package com.example.u17.module_login.activity;

import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
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
 * @Time:2016/9/9
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, android.app.LoaderManager.LoaderCallbacks<Cursor> {
    //返回图标
    @BindView(R.id.icon_image_view_login_back)
    ImageView mLoginBack;
    //邮箱快速注册
    @BindView(R.id.id_mobile_register)
    TextView mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_image_view_login_back:
                //返回上一个界面
                finish();
                break;
            case R.id.id_mobile_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
