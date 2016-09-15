package com.example.u17.module_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.u17.R;
import com.example.u17.module_home.bean.Boutique;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/15
 */
public class BoutiqueRecyclerAdapter extends RecyclerView.Adapter<RecViewHolder> {

    private Context context;
    private String title;
    private List<Boutique.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> data = new ArrayList<>();

    public BoutiqueRecyclerAdapter(Context context, List<Boutique.DataBean.ReturnDataBean.ComicListsBean.ComicsBean> data,String title) {
        this.context = context;
        this.data = data;
        this.title = title;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.boutique_child_item_view,null);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, final int position) {
        String coverUrl = data.get(position).getCover();
        Picasso.with(context).load(coverUrl).placeholder(R.drawable.main_recycler_image_default).into(holder.ivCover);
        if (title.equals("VIP会员漫画")){
            holder.ivType.setVisibility(View.VISIBLE);
        }
        if (title.equals("订阅漫画")){
            holder.ivType.setImageResource(R.drawable.main_boutique_subscribe);
            holder.ivType.setVisibility(View.VISIBLE);
        }
        String name = data.get(position).getName();
        holder.tvName.setText(name);
        String description = data.get(position).getShort_description();
        holder.tvDescription.setText(description);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int comicId = data.get(position).getComicId();
                Intent intent = new Intent(context, StatisticsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("comicId",comicId);
                intent.putExtra("bundle", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
