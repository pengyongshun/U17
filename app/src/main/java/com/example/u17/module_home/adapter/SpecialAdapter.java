package com.example.u17.module_home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.bean.BoutiqueSpecial;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecialAdapter extends AbsBaseAdapter<BoutiqueSpecial.DataBean.ReturnDataBean.ComicsBean> {
    public SpecialAdapter(Context context, List<BoutiqueSpecial.DataBean.ReturnDataBean.ComicsBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.special_item_view,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int specialType = data.get(position).getSpecialType();
        if (specialType == 2){
            viewHolder.type.setText("次元");
            viewHolder.type.setBackgroundColor(Color.GRAY);
        }else{
            viewHolder.type.setText("漫画");
            viewHolder.type.setBackgroundColor(context.getResources().getColor(R.color.comicGreen));
        }
        viewHolder.title.setText(data.get(position).getTitle());
        viewHolder.subTitle.setText(data.get(position).getSubTitle());

        Picasso.with(context).load(data.get(position).getCover()).placeholder(R.drawable.main_recycler_image_default).into(viewHolder.cover);
        return convertView;
    }
    class ViewHolder{
        @BindView(R.id.tv_special_type)
        TextView type;
        @BindView(R.id.tv_special_title)
        TextView title;
        @BindView(R.id.tv_special_subTitle)
        TextView subTitle;
        @BindView(R.id.iv_special_cover)
        ImageView cover;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
