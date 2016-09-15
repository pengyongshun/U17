package com.example.u17.module_home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.u17.module_home.bean.RankingTab;

import java.util.List;

public class RankingViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<RankingTab.DataBean.ReturnDataBean.RankinglistBean> rankingTabs;
    public RankingViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public RankingViewPagerAdapter(FragmentManager fm, List<Fragment> fragments,List<RankingTab.DataBean.ReturnDataBean.RankinglistBean> rankingTabs) {
        super(fm);
        this.fragments = fragments;
        this.rankingTabs = rankingTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments!=null?fragments.get(position):null;
    }

    @Override
    public int getCount() {
        return fragments!=null?fragments.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return rankingTabs!=null?rankingTabs.get(position).getTitle():null;
    }
}
