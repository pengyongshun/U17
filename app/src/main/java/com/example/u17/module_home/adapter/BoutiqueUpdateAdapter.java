package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.bean.BoutiqueUpdate;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoutiqueUpdateAdapter extends AbsBaseAdapter<BoutiqueUpdate.DataBean.ReturnDataBean.ComicsBean> {
    public BoutiqueUpdateAdapter(Context context, List<BoutiqueUpdate.DataBean.ReturnDataBean.ComicsBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.boutique_update_item,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.description.setText(data.get(position).getDescription());
        viewHolder.grade.setText(data.get(position).getGrade());
        Picasso.with(context).load(data.get(position).getCover()).placeholder(R.drawable.u17_default_comic_cover_bg).into(viewHolder.ivCover);
        return convertView;
    }
    class ViewHolder{
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_grade)
        TextView grade;
        @BindView(R.id.tv_description)
        TextView description;
        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
