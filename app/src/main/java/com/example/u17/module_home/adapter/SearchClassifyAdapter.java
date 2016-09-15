package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.bean.BoutiqueClassify;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 时光彼岸之外 on 2016/6/16.
 */
public class SearchClassifyAdapter extends AbsBaseAdapter<BoutiqueClassify.DataBean.ReturnDataBean.RankinglistBean> {
    public SearchClassifyAdapter(Context context, List<BoutiqueClassify.DataBean.ReturnDataBean.RankinglistBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.classify_item_view,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(data.get(position).getIconSortName());
        Picasso.with(context).load(data.get(position).getCover()).placeholder(R.drawable.main_recycler_image_default).into(viewHolder.cover);
        return convertView;
    }
    static class ViewHolder{
        @BindView(R.id.iv_classify_cover)
        ImageView cover;

        @BindView(R.id.tv_iconSortName)
        TextView name;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
