package com.example.u17.moudle_search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.u17.R;
import com.example.u17.moudle_search.bean.SerachListBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/8
 */
public class SerachListGvHeadAdapter extends BaseAdapter {
    private List<SerachListBean.DataBean.ReturnDataBean.TopListBean> beanList;
    private Context context;
    private LayoutInflater mInflater;
    public SerachListGvHeadAdapter(Context context, List<SerachListBean.DataBean.ReturnDataBean.TopListBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        mInflater=LayoutInflater.from(context);
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
        ViewHodler hodler=null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.item_fragment_serach_list_head_iv,parent,false);
            hodler=new ViewHodler(convertView);
        }else {
            hodler= (ViewHodler) convertView.getTag();
        }

        SerachListBean.DataBean.ReturnDataBean.TopListBean topListBean = beanList.get(position);
        String cover = topListBean.getCover();
        hodler.imageView.setImageResource(R.mipmap.ic_launcher);
        Picasso.with(context).load(cover).into(hodler.imageView);
        return convertView;
    }
    class ViewHodler{
        @BindView(R.id.item_fragment_serach_list_iv_gridview_head)
        ImageView imageView;
        public ViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
