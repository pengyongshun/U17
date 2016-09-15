package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.u17.R;
import com.example.u17.module_home.bean.ComicContent;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicContentAdapter extends AbsBaseAdapter<ComicContent.DataBean.ReturnDataBean.ImageListBean> {

    public ComicContentAdapter(Context context, List<ComicContent.DataBean.ReturnDataBean.ImageListBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.comic_content_item,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String imageURl = data.get(position).getLocation();
        Picasso.with(context).load(imageURl).placeholder(R.drawable.u17_default_comic_cover_bg).into(viewHolder.ivContent);
        return convertView;
    }
    class ViewHolder {
        @BindView(R.id.iv_comic_content)
        ImageView ivContent;

        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this, view);
        }
    }
}
