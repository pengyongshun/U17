package com.example.u17.moudle_search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.moudle_search.bean.SerachDetialTodayBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/9
 */
public class SerachDetialTodayAdapter extends BaseAdapter {
    private List<SerachDetialTodayBean.DataBean.ReturnDataBean.ComicsBean>beanList=new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public SerachDetialTodayAdapter(Context context, List<SerachDetialTodayBean.DataBean.ReturnDataBean.ComicsBean> beanList) {
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
        SerachDetialTodayViewHodler hodler=null;
        if (convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.item_fragment_serach_detial_today_prlv,parent,false);
            hodler=new SerachDetialTodayViewHodler(convertView);
        }else {
            hodler= (SerachDetialTodayViewHodler) convertView.getTag();
        }
        //更新UI
        SerachDetialTodayBean.DataBean.ReturnDataBean.ComicsBean comicsBean = beanList.get(position);
        String cover = comicsBean.getCover();
        String conTag = comicsBean.getConTag();
        String description = comicsBean.getDescription();
        String name = comicsBean.getName();
        List<String> tags = comicsBean.getTags();
        hodler.ivCover.setImageResource(R.mipmap.ic_launcher);
        if (!cover.isEmpty()){
            Picasso.with(context).load(cover).into(hodler.ivCover);
        }
        hodler.tvName.setText(name);
        hodler.tvDescription.setText(description);
        int size = tags.size();
        StringBuffer buffer=new StringBuffer();
        for (int i = 0; i < size; i++) {
            String content = tags.get(i);
            buffer.append(content).append("");
        }
        hodler.tvTags.setText(buffer.toString());
        hodler.tvClicknum.setText("点击量："+conTag);
        if (position==0){
            hodler.ivConTag.setVisibility(View.VISIBLE);
            hodler.ivConTag.setImageResource(R.drawable.icon_ranking_first);
        }else if (position==1){
            hodler.ivConTag.setVisibility(View.VISIBLE);
            hodler.ivConTag.setImageResource(R.drawable.icon_ranking_second);
        }else if (position==2){
            hodler.ivConTag.setVisibility(View.VISIBLE);
            hodler.ivConTag.setImageResource(R.drawable.icon_ranking_third);
        }else {
            hodler.ivConTag.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }
    class SerachDetialTodayViewHodler{
        @BindView(R.id.item_serach_detial_today_iv_cover)
        ImageView ivCover;
        @BindView(R.id.item_serach_detial_today_iv_pay)
        ImageView ivPay;
        @BindView(R.id.item_serach_detial_today_tv_clicknum)
        TextView tvClicknum;
        @BindView(R.id.item_serach_detial_today_iv_conTag)
        ImageView ivConTag;
        @BindView(R.id.item_serach_detial_today_tv_description)
        TextView tvDescription;
        @BindView(R.id.item_serach_detial_today_tv_name)
        TextView tvName;
        @BindView(R.id.item_serach_detial_today_tv_tags)
        TextView tvTags;
        public SerachDetialTodayViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
