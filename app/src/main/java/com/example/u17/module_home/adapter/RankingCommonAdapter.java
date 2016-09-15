package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.bean.Comics;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankingCommonAdapter extends AbsBaseAdapter<Comics.DataBean.ReturnDataBean.ComicsBean> {
    private String rankingTab;
    private Animation animation;
    public RankingCommonAdapter(Context context, List<Comics.DataBean.ReturnDataBean.ComicsBean> data,String rankingTab) {
        super(context, data);
        this.rankingTab = rankingTab;
        animation = AnimationUtils.loadAnimation(context, R.anim.ranking_listview_item_scale);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.ranking_list_item,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.comicRankNumber.setText("");
        if (position==0){
            viewHolder.comicRankNumber.setBackgroundResource(R.drawable.icon_comic_first);
        }else if (position==1){
            viewHolder.comicRankNumber.setBackgroundResource(R.drawable.icon_comic_second);
        }else if (position==2){
            viewHolder.comicRankNumber.setBackgroundResource(R.drawable.icon_comic_third);
        }else {
            viewHolder.comicRankNumber.setBackgroundResource(R.drawable.icon_comic_forth);
            viewHolder.comicRankNumber.setText(""+(position+1));
        }
        Comics.DataBean.ReturnDataBean.ComicsBean comicsBean = getData().get(position);
        Picasso.with(context).load(comicsBean.getCover()).placeholder(R.drawable.u17_default_comic_cover_bg).into(viewHolder.comicCover);

        viewHolder.comicName.setText(comicsBean.getName());
        int flag = comicsBean.getFlag();
        if (flag==3){
            viewHolder.comicIsVip.setVisibility(View.VISIBLE);
        }else {
            viewHolder.comicIsVip.setVisibility(View.GONE);
        }
        List<String> tags = comicsBean.getTags();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < tags.size(); i++) {
            sb.append(tags.get(i)+" ");
        }
        viewHolder.comicTagsAndAuthor.setText(sb+"| "+comicsBean.getAuthor());
        viewHolder.comicDescription.setText(comicsBean.getDescription());
        viewHolder.comicConTag.setText(rankingTab+"值");
        viewHolder.comicConTagValue.setText("");
        double conTag = Double.parseDouble(comicsBean.getConTag());
        DecimalFormat df = new DecimalFormat("0.00");
        if (conTag<10000){
            viewHolder.comicConTagValue.setText(comicsBean.getConTag());
        }else if (conTag>=10000 &&conTag<100000000){
            String format = df.format(conTag / 10000);
            viewHolder.comicConTagValue.setText(format+" 万");
        }else if (conTag>100000000){
            String format = df.format(conTag / 100000000);
            viewHolder.comicConTagValue.setText(format+" 亿");
        }
        convertView.startAnimation(animation);
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
        @BindView(R.id.iv_comic_is_vip)
        ImageView comicIsVip;
        @BindView(R.id.tv_comic_rank_number)
        TextView comicRankNumber;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
