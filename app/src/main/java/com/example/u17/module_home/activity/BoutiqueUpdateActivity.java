package com.example.u17.module_home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.u17.R;
import com.example.u17.module_home.adapter.BoutiqueUpdateAdapter;
import com.example.u17.module_home.bean.BoutiqueUpdate;
import com.example.u17.module_home.url.HomeUrl;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BoutiqueUpdateActivity extends AppCompatActivity {
    private BoutiqueUpdateAdapter adapter;
    private List<BoutiqueUpdate.DataBean.ReturnDataBean.ComicsBean> data;
    @BindView(R.id.boutique_refresh_listview)
    PullToRefreshListView mListView;

    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boutique_update);
        ButterKnife.bind(this);
        data = new ArrayList<>();
        adapter = new BoutiqueUpdateAdapter(this,data);
        mListView.setAdapter(adapter);
        initData();
        addListener();
    }

    private void addListener() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                data.clear();
                OkHttpTask.newInstance(BoutiqueUpdateActivity.this).enqueue(new IOkTaskCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        BoutiqueUpdate update = gson.fromJson(result, BoutiqueUpdate.class);
                        if (update != null && update.getData().getReturnData().getComics() != null){
                            data.addAll(update.getData().getReturnData().getComics());
                            adapter.notifyDataSetChanged();
                            mListView.onRefreshComplete();
                        }
                    }
                }).start(HomeUrl.BOUTIQUE_UPDATE_PATH+page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                OkHttpTask.newInstance(BoutiqueUpdateActivity.this).enqueue(new IOkTaskCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        BoutiqueUpdate update = gson.fromJson(result, BoutiqueUpdate.class);
                        if (update != null && update.getData().getReturnData().getComics() != null){
                            data.addAll(update.getData().getReturnData().getComics());
                            adapter.notifyDataSetChanged();
                            mListView.onRefreshComplete();
                        }
                    }
                }).start(HomeUrl.BOUTIQUE_UPDATE_PATH+page);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int comicId = adapter.getData().get(position-1).getComicId();
                Intent intent = new Intent(BoutiqueUpdateActivity.this, StatisticsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("comicId",comicId);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BoutiqueUpdate update = gson.fromJson(result, BoutiqueUpdate.class);
                if (update != null && update.getData().getReturnData().getComics() != null){
                    data.addAll(update.getData().getReturnData().getComics());
                    adapter.notifyDataSetChanged();
                }
            }
        }).start(HomeUrl.BOUTIQUE_UPDATE_PATH+page);
    }

    @OnClick(R.id.iv_boutique_update_back)
    public void click(View view){
        onBackPressed();
    }
}
