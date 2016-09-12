package com.example.u17.module_mine.activity;



import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.u17.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/10
 */
public class GameActivity extends AppCompatActivity implements View.OnClickListener{
   //返回图标
    @BindView(R.id.activity_game_image_view_back)
    ImageView mGameBack;
    //ListView
    @BindView(R.id.game_list_view)
    ListView mListView;


    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        mGameBack.setOnClickListener(this);

        initListView();
        initHeadView();
    }

    private void initListView() {

    }

    private void initHeadView() {

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.activity_game_image_view_back:
                finish();
                break;
            default:
                break;
        }

    }
}
