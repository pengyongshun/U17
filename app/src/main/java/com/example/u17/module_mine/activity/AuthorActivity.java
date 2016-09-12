package com.example.u17.module_mine.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.u17.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/11
 */
public class AuthorActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.author_image_view_back)
    ImageView mAuthorBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_author);
        ButterKnife.bind(this);
        mAuthorBack.setOnClickListener(this);

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
