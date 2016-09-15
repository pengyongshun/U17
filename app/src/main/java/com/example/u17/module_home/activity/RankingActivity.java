package com.example.u17.module_home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.u17.R;
import com.example.u17.module_home.adapter.RankingViewPagerAdapter;
import com.example.u17.module_home.bean.RankingTab;
import com.example.u17.module_home.fragment.RankingCommonFragment;
import com.example.u17.module_home.url.HomeUrl;
import com.google.gson.Gson;
import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RankingActivity extends AppCompatActivity {

    private List<RankingTab.DataBean.ReturnDataBean.RankinglistBean> rankingTabs;
    private List<Fragment> fragments;
    private RankingViewPagerAdapter pagerAdapter;

    @BindView(R.id.tab_layout_ranking)
    TabLayout mRankingTab;
    @BindView(R.id.view_pager_ranking)
    ViewPager mRankingViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        rankingTabs = new ArrayList<>();
        pagerAdapter = new RankingViewPagerAdapter(getSupportFragmentManager(), fragments, rankingTabs);
        mRankingViewPager.setAdapter(pagerAdapter);
        initTabData();
    }
    private void initTabData() {
        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                RankingTab rankingTab = gson.fromJson(result, RankingTab.class);
                if (rankingTab != null && rankingTab.getData().getReturnData().getRankinglist() != null) {
                    List<RankingTab.DataBean.ReturnDataBean.RankinglistBean> rankinglist = rankingTab.getData().getReturnData().getRankinglist();
                    rankingTabs.addAll(rankinglist);
                    mRankingTab.setupWithViewPager(mRankingViewPager);
                    initFragments();
                    pagerAdapter.notifyDataSetChanged();
                }
            }
        }).start(HomeUrl.RANKING_TAB_PATH);
    }

    private void initFragments() {
        for (int i = 0; i < rankingTabs.size(); i++) {
            int argValue = Integer.parseInt(rankingTabs.get(i).getArgValue());
            String urlPath = HomeUrl.RANKING_COMICS_PATH + argValue;
            fragments.add(RankingCommonFragment.newInstance(urlPath, rankingTabs.get(i).getTitle()));
        }
    }

    @OnClick(R.id.iv_ranking_back)
    public void click(View view) {
        onBackPressed();
    }
}
