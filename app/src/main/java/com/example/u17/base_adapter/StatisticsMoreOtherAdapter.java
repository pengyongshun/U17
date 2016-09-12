package com.example.u17.base_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.moudle_search.bean.StatisticsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/12
 */
public class StatisticsMoreOtherAdapter extends BaseAdapter {
    private List<StatisticsBean.OtherWorksBean> beanList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public StatisticsMoreOtherAdapter(Context context, List<StatisticsBean.OtherWorksBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList==null?0:beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SMOViewHodler smoViewHodler=null;
        if (convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.item_fragment_statistics_more_detial_gridview,parent,false);
            smoViewHodler=new SMOViewHodler(convertView);
        }else {
            smoViewHodler= (SMOViewHodler) convertView.getTag();
        }
        //跟新UI
        StatisticsBean.OtherWorksBean otherWorksBean = beanList.get(position);
        String coverUrl = otherWorksBean.getCoverUrl();
        String name = otherWorksBean.getName();
        smoViewHodler.tvName.setText(name);
        smoViewHodler.ivIcon.setImageResource(R.drawable.main_recycler_image_default);
        Picasso.with(context).load(coverUrl).into(smoViewHodler.ivIcon);
        return convertView;
    }
    class SMOViewHodler{
        @BindView(R.id.item_fragment_statistics_more_detial_gridview_iv_icon)
        ImageView ivIcon;
        @BindView(R.id.item_fragment_statistics_more_detial_gridview_tv_name)
        TextView tvName;
        public SMOViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
