package com.example.u17.base_adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Author:pengyongshun
 * @Desc: 引导页的适配器
 * @Time:2016/9/8
 */
public class GuideAdapter extends PagerAdapter {
    private List<View> views;

    public GuideAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views==null?0:views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
