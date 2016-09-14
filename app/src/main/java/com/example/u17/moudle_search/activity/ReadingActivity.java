package com.example.u17.moudle_search.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.u17.R;
import com.example.u17.base_adapter.ReadingAdapter;
import com.example.u17.base_http.BaseUrl;
import com.example.u17.moudle_search.ascytask.ReadingAscytask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadingActivity extends AppCompatActivity {
    @BindView(R.id.activity_reading_lv_content)
    public ListView mListView;
    @BindView(R.id.activity_reading_tool_content)
    public Toolbar mToolbar;
    private List<String> urlList=new ArrayList<>();
    private ReadingAdapter mReadingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        iniAdapter();
        loadData();
    }

    private void loadData() {
        new ReadingAscytask(new ReadingAscytask.ReadingCallBack() {
            @Override
            public void callBack(List<String> urls) {
               if (urls==null){
                   return;
               }
                urlList.addAll(urls);
                mReadingAdapter.notifyDataSetChanged();
            }
        }).execute(BaseUrl.READING_URL);
    }

    private void iniAdapter() {
        if (urlList==null){
            return;
        }
        mReadingAdapter = new ReadingAdapter(this,urlList);
        mListView.setAdapter(mReadingAdapter);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //按下时显示
                mToolbar.setVisibility(View.VISIBLE);
                break;
            case MotionEvent.ACTION_UP:
                //抬起时,toobar消失
                mToolbar.setVisibility(View.INVISIBLE);
                break;
        }
        return false;
    }



}
