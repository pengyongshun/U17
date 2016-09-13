package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.u17.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsHeadPhotoActivity extends AppCompatActivity {
    @BindView(R.id.activity_statistics_head_photo_iv_image)
    public ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_head_photo);
        ButterKnife.bind(this);
        //从上一个界面获取图片的url
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String url = bundle.getString("url");
        Picasso.with(this).load(url).into(imageView);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_statistics_head_photo_iv_back:
                finish();
                break;
            case R.id.activity_statistics_head_photo_tv_save:
                //TODO 点击保存到磁盘中
                Toast.makeText(StatisticsHeadPhotoActivity.this, "点击保存到磁盘中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.activity_statistics_head_photo_tv_share:
                //TODO 点击分享  到第三方
                Toast.makeText(StatisticsHeadPhotoActivity.this, "点击分享  到第三方", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
