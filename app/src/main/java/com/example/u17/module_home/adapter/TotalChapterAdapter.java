package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.u17.R;
import com.example.u17.module_home.bean.ComicDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 时光彼岸之外 on 2016/6/21.
 */
public class TotalChapterAdapter extends AbsBaseAdapter<ComicDetail.DataBean.ReturnDataBean.ChapterListBean> {
    public TotalChapterAdapter(Context context, List<ComicDetail.DataBean.ReturnDataBean.ChapterListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.comic_catalog_grid_item, null);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ComicDetail.DataBean.ReturnDataBean.ChapterListBean chapterListBean = data.get(position);
        String name = chapterListBean.getName();
        holder.comicCatalog.setText(name);
//        String chapterNum = String.valueOf(position + 1);
//        holder.comicCatalog.setText(chapterNum);
        int type = data.get(position).getType();
        if (type==2){
            holder.chapterIsVip.setVisibility(View.VISIBLE);
            holder.chapterIsVip.setImageResource(R.drawable.detail_chapter_pay);
        }else if (type==3){
            holder.chapterIsVip.setVisibility(View.VISIBLE);
            holder.chapterIsVip.setImageResource(R.drawable.detail_chapter_vip);
        }else {
            holder.chapterIsVip.setVisibility(View.GONE);
        }
        int is_new = data.get(position).getIs_new();
        if (is_new==1){
            holder.chapterIsNew.setVisibility(View.VISIBLE);
        }else {
            holder.chapterIsNew.setVisibility(View.GONE);
        }
        return convertView;
    }
    class ViewHolder {
        @BindView(R.id.tv_comic_catalog)
        TextView comicCatalog;
        @BindView(R.id.iv_detail_chapter_vip)
        ImageView chapterIsVip;
        @BindView(R.id.iv_comic_detail_chapter_new)
        ImageView chapterIsNew;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }

    }
}
