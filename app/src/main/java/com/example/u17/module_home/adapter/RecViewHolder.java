package com.example.u17.module_home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.u17.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/15
 */
public class RecViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_boutique_cover)
    public ImageView ivCover;
    @BindView(R.id.iv_vip_or_subscribe)
    public ImageView ivType;
    @BindView(R.id.tv_boutique_name)
    public TextView tvName;
    @BindView(R.id.tv_boutique_short_description)
    public TextView tvDescription;
    @BindView(R.id.ll_recycler_item)
    public LinearLayout item;
    public RecViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
