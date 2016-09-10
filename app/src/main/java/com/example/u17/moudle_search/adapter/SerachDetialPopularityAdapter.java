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
 * @Time:2016/9/10
 */
public class SerachDetialPopularityAdapter extends BaseAdapter {
    private List<SerachDetialTodayBean.DataBean.ReturnDataBean.ComicsBean> beanList=new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public SerachDetialPopularityAdapter(Context context, List<SerachDetialTodayBean.DataBean.ReturnDataBean.ComicsBean> beanList) {
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
        SerachDetialTPopularityViewHodler hodler=null;
        if (convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.item_fragment_serach_detial_popularity_prlv,parent,false);
            hodler=new SerachDetialTPopularityViewHodler(convertView);
        }else {
            hodler= (SerachDetialTPopularityViewHodler) convertView.getTag();
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
        hodler.tvClicknum.setText("总点击："+conTag);
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
    class SerachDetialTPopularityViewHodler{
        @BindView(R.id.item_serach_detial_popularity_iv_cover)
        ImageView ivCover;
        @BindView(R.id.item_serach_detial_popularity_iv_pay)
        ImageView ivPay;
        @BindView(R.id.item_serach_detial_popularity_tv_clicknum)
        TextView tvClicknum;
        @BindView(R.id.item_serach_detial_popularity_iv_conTag)
        ImageView ivConTag;
        @BindView(R.id.item_serach_detial_popularity_tv_description)
        TextView tvDescription;
        @BindView(R.id.item_serach_detial_popularity_tv_name)
        TextView tvName;
        @BindView(R.id.item_serach_detial_popularity_tv_tags)
        TextView tvTags;
        public SerachDetialTPopularityViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
