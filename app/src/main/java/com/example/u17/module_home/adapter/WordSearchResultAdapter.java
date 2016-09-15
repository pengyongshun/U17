package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.u17.R;
import com.example.u17.module_home.bean.WordSearchResult;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordSearchResultAdapter extends AbsBaseAdapter<WordSearchResult.DataBean.ReturnDataBean.ComicsBean> {
    public WordSearchResultAdapter(Context context, List<WordSearchResult.DataBean.ReturnDataBean.ComicsBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.search_result_item,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(data.get(position).getCover()).placeholder(R.drawable.main_recycler_image_default).into(viewHolder.comicCover);
        viewHolder.comicName.setText(data.get(position).getName());
        List<String> tags = data.get(position).getTags();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tags.size(); i++) {
            sb.append(tags.get(i)+" ");
        }
        viewHolder.comicTagsAndAuthor.setText(sb+" | "+data.get(position).getAuthor());
        viewHolder.comicDescription.setText(data.get(position).getDescription());
        int flag = Integer.parseInt(data.get(position).getFlag());
        if (flag==3){
            viewHolder.comicFlag.setVisibility(View.VISIBLE);
        }else if(flag==1){
            viewHolder.comicFlag.setImageResource(R.drawable.icon_comic_pay);
        }else {
            viewHolder.comicFlag.setVisibility(View.GONE);
        }
        double conTag = Double.parseDouble(data.get(position).getConTag());
        DecimalFormat df = new DecimalFormat("0.00");
        if (conTag<10000){
            viewHolder.comicConTag.setText("总点击："+data.get(position).getConTag());
        }else if (conTag>=10000 &&conTag<100000000){
            String format = df.format(conTag / 10000);
            viewHolder.comicConTag.setText("总点击："+format+" 万");
        }else if (conTag>100000000){
            String format = df.format(conTag / 100000000);
            viewHolder.comicConTag.setText("总点击："+format+" 亿");
        }
        return convertView;
    }
    class ViewHolder{
        @BindView(R.id.iv_comic_cover)
        ImageView comicCover;
        @BindView(R.id.tv_comic_name)
        TextView comicName;
        @BindView(R.id.tv_comic_tags_author)
        TextView comicTagsAndAuthor;
        @BindView(R.id.tv_comic_description)
        TextView comicDescription;
        @BindView(R.id.tv_comic_conTag)
        TextView comicConTag ;
        @BindView(R.id.tv_comic_conTag_value)
        TextView comicConTagValue ;
        @BindView(R.id.iv_comic_flag)
        ImageView comicFlag;
        /*@BindView(R.id.tv_comic_rank_number)
        TextView comicRankNumber;*/

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
