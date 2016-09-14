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
public class SerachDetialCategoryAdapter extends BaseAdapter{
    private List<SerachDetialTodayBean> beanList=new ArrayList<>();
    private Context context;
    private LayoutInflater mLayoutInflater;

    public SerachDetialCategoryAdapter(Context context, List<SerachDetialTodayBean> beanList) {
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
            convertView=mLayoutInflater.inflate(R.layout.item_fragment_serach_detial_category_prlv,parent,false);
            hodler=new SerachDetialTPopularityViewHodler(convertView);
        }else {
            hodler= (SerachDetialTPopularityViewHodler) convertView.getTag();
        }
        //更新UI
        SerachDetialTodayBean comicsBean = beanList.get(position);
        String cover = comicsBean.getCover();
        String conTag = comicsBean.getConTag();
        String description = comicsBean.getDescription();
        String name = comicsBean.getName();
        List<String> tags = comicsBean.getTags();
        if (!cover.isEmpty()){
            hodler.ivCover.setImageResource(R.drawable.main_recycler_image_default);
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
        long count = Long.parseLong(conTag);
        if (count>900000000){
            hodler.ivPay.setImageResource(R.drawable.icon_comic_vip);
        }else {
            hodler.ivPay.setImageResource(R.drawable.icon_comic_pay);
        }
        return convertView;
    }
    class SerachDetialTPopularityViewHodler{
        @BindView(R.id.item_serach_detial_category_iv_cover)
        ImageView ivCover;
        @BindView(R.id.item_serach_detial_category_iv_pay)
        ImageView ivPay;
        @BindView(R.id.item_serach_detial_category_tv_clicknum)
        TextView tvClicknum;
        @BindView(R.id.item_serach_detial_category_tv_description)
        TextView tvDescription;
        @BindView(R.id.item_serach_detial_category_tv_name)
        TextView tvName;
        @BindView(R.id.item_serach_detial_category_tv_tags)
        TextView tvTags;
        public SerachDetialTPopularityViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
