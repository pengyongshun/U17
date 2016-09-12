package com.example.u17.module_bookrack.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.u17.R;

import java.util.List;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/12
 */
public class BookrackPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private Context context;

    public BookrackPageAdapter(FragmentManager fm, Context context,List<Fragment> fragments) {
        super(fm);
        this.context=context;
        this.fragments = fragments;
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
        String[] stringArray = context.getResources().getStringArray(R.array.bookshelfTabs);
        return stringArray[position];
    }
}
