package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.u17.module_home.bean.HomeListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/14
 */
public class HomeELVAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> beanList=new ArrayList<>();
    private Map<String,List<HomeListBean.DataBean.ReturnDataBean.ComicListsBean>> map=new HashMap<>();
    @Override
    public int getGroupCount() {
        return beanList==null?0:beanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String key = beanList.get(groupPosition);
        List<HomeListBean.DataBean.ReturnDataBean.ComicListsBean> comicListsBeen = map.get(key);
        return comicListsBeen==null?0:comicListsBeen.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return beanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = beanList.get(groupPosition);
        List<HomeListBean.DataBean.ReturnDataBean.ComicListsBean> comicListsBeen = map.get(key);
        return beanList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    class HomeELVViewHodler{
        public HomeELVViewHodler(View view) {
           // ButterKnife
        }
    }
}
