package com.example.u17.base_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.u17.R;
import com.example.u17.base_uitls.net_uitls.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/13
 */
public class ReadingAdapter extends BaseAdapter {
    private List<String> urls;
    private Context context;
    private LayoutInflater inflater;

    public ReadingAdapter(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return urls==null?0:urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReadingViewHodler hodler=null;
        if (convertView==null){
            convertView=inflater.inflate(R.layout.item_activity_reading_lv,parent,false);
            hodler=new ReadingViewHodler(convertView);
        }else {
            hodler= (ReadingViewHodler) convertView.getTag();
        }
        String url = urls.get(position);
        if (url==null){
            return null;
        }
        hodler.imageView.setImageResource(R.drawable.main_recycler_image_default);

       // ImageLoader.init(context).load(url,hodler.imageView);
        Picasso.with(context).load(url).into(hodler.imageView);
        return convertView;
    }
    class ReadingViewHodler{
        @BindView(R.id.item_activity_reading_iv_content)
        ImageView imageView;
        public ReadingViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
