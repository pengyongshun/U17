package com.example.u17.module_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.bean.Boutique;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoutiqueExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Boutique.DataBean.ReturnDataBean.ComicListsBean> comicLists = new ArrayList<>();
    private Map<String, List<Boutique.DataBean.ReturnDataBean.ComicListsBean.ComicsBean>> dataMap = new HashMap<>();

    public BoutiqueExpandAdapter(Context context, List<Boutique.DataBean.ReturnDataBean.ComicListsBean> comicLists, Map<String, List<Boutique.DataBean.ReturnDataBean.ComicListsBean.ComicsBean>> dataMap) {
        this.context = context;
        this.comicLists = comicLists;
        this.dataMap = dataMap;
    }

    @Override
    public int getGroupCount() {
        return comicLists == null ? 0 : comicLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getGroupType(int groupPosition) {
        int comicType = comicLists.get(groupPosition).getComicType();
        return comicType - 1;
    }

    @Override
    public int getGroupTypeCount() {
        return 2;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        int type = comicLists.get(groupPosition).getComicType();
        GroupViewHolder1 groupViewHolder1 = null;
        GroupViewHolder2 groupViewHolder2 = null;
        if (view == null) {
            switch (type) {
                case 1:
                    view = LayoutInflater.from(context).inflate(R.layout.boutique_group_view, null);
                    groupViewHolder1 = new GroupViewHolder1(view);
                    break;
                case 2:
                    view = LayoutInflater.from(context).inflate(R.layout.boutique_group_view2, null);
                    groupViewHolder2 = new GroupViewHolder2(view);
                    break;
            }
        } else {
            switch (type) {
                case 1:
                    groupViewHolder1 = (GroupViewHolder1) view.getTag();
                    break;
                case 2:
                    groupViewHolder2 = (GroupViewHolder2) view.getTag();
                    break;
            }
        }
        Boutique.DataBean.ReturnDataBean.ComicListsBean comicListsBean = comicLists.get(groupPosition);
        switch (type) {

            case 1:
                String title1 = comicListsBean.getItemTitle();
                groupViewHolder1.tvTitle.setText(title1);
                String description = comicListsBean.getDescription();
                groupViewHolder1.tvDescription.setText(description);
                String iconUrl1 = comicListsBean.getTitleIconUrl();
                Picasso.with(context).load(iconUrl1).placeholder(R.drawable.u17_default_comic_cover_bg).into(groupViewHolder1.ivIcon);
                break;
            case 2:
                String title2 = comicLists.get(groupPosition).getItemTitle();
                groupViewHolder2.tvTitle.setText(title2);
                String iconUrl2 = comicLists.get(groupPosition).getTitleIconUrl();
                Picasso.with(context).load(iconUrl2).placeholder(R.drawable.u17_default_comic_cover_bg).into(groupViewHolder2.ivIcon);
                break;
        }

        return view;
    }

    class GroupViewHolder1 {
        @BindView(R.id.iv_titleIconUrl_1)
        public ImageView ivIcon;

        @BindView(R.id.tv_itemTitle_1)
        public TextView tvTitle;

        @BindView(R.id.tv_description_1)
        public TextView tvDescription;

        public GroupViewHolder1(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }
    }

    class GroupViewHolder2 {
        @BindView(R.id.iv_titleIconUrl_2)
        public ImageView ivIcon;

        @BindView(R.id.tv_itemTitle_2)
        public TextView tvTitle;

        public GroupViewHolder2(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.boutique_header_recyclerview)
        public RecyclerView recyclerView;
        public ChildViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder2 {
        @BindView(R.id.iv_boutique_cover)
        public ImageView ivCover;
        @BindView(R.id.tv_boutique_name)
        public TextView tvName;
        @BindView(R.id.tv_boutique_short_description)
        public TextView tvDescription;
        @BindView(R.id.tv_boutique_author_name)
        public TextView tvAuthor;

        public ChildViewHolder2(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        int comicType = comicLists.get(groupPosition).getComicType();
        return comicType - 1;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View childView = convertView;
        int type = comicLists.get(groupPosition).getComicType();
        ChildViewHolder viewHolder1 = null;
        ChildViewHolder2 viewHolder2 = null;
        if (childView == null) {
            switch (type) {
                case 1:
                    childView = LayoutInflater.from(context).inflate(R.layout.boutique_child_view, null);
                    viewHolder1 = new ChildViewHolder(childView);
                    break;
                case 2:
                    childView = LayoutInflater.from(context).inflate(R.layout.boutique_child_view2, null);
                    viewHolder2 = new ChildViewHolder2(childView);
                    break;
            }
        } else {
            switch (type) {
                case 1:
                    viewHolder1 = (ChildViewHolder) childView.getTag();
                    break;
                case 2:
                    viewHolder2 = (ChildViewHolder2) childView.getTag();
                    break;
            }
        }
        switch (type) {
            case 1:
                String title = comicLists.get(groupPosition).getItemTitle();
                viewHolder1.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                BoutiqueRecyclerAdapter boutiqueRecyclerAdapter = new BoutiqueRecyclerAdapter(context, dataMap.get(title), title);
                viewHolder1.recyclerView.setAdapter(boutiqueRecyclerAdapter);
                break;
            case 2:
                String titleVaule = comicLists.get(groupPosition).getItemTitle();
                String coverUrl = dataMap.get(titleVaule).get(childPosition).getCover();
                Picasso.with(context).load(coverUrl).placeholder(R.drawable.u17_default_comic_cover_bg).into(viewHolder2.ivCover);
                String name = dataMap.get(titleVaule).get(childPosition).getName();
                viewHolder2.tvName.setText(name);
                String author = dataMap.get(titleVaule).get(childPosition).getAuthor_name();
                viewHolder2.tvAuthor.setText(author);
                String description = dataMap.get(titleVaule).get(childPosition).getDescription();
                viewHolder2.tvDescription.setText(description);
                break;
        }

        return childView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
