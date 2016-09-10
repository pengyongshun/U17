package com.example.u17.moudle_search.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/9
 */
public class SerachDetialTabAdapter extends FragmentStatePagerAdapter {
    private List<String>titleList;
    private List<Fragment> fragmentList;
    public SerachDetialTabAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String>titleList) {
        super(fm);
        this.titleList=titleList;
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList==null?null:fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList==null?null:titleList.get(position);
    }


}
