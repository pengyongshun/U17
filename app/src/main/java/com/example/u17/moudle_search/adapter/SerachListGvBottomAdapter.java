package com.example.u17.moudle_search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
public class SerachListGvBottomAdapter extends BaseAdapter {
    private List<SerachListBean.DataBean.ReturnDataBean.RankingListBean> beanList;
    private Context context;
    private LayoutInflater mInflater;

    public SerachListGvBottomAdapter(Context context, List<SerachListBean.DataBean.ReturnDataBean.RankingListBean> beanList) {
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
        ViewHolder holder=null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.item_fragment_serach_list_gridview_two,parent,false);
            holder=new ViewHolder(convertView);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //跟新UI
        SerachListBean.DataBean.ReturnDataBean.RankingListBean rankingListBean = beanList.get(position);
        String cover = rankingListBean.getCover();
        String sortName = rankingListBean.getSortName();

        if (!cover.isEmpty()){
            Picasso.with(context).load(cover).placeholder(R.drawable.u17_default_comic_cover_bg).into(holder.ivBottom);

        }
         holder.tvBottom.setText(sortName);
        return convertView;
    }
    class ViewHolder {
        @BindView(R.id.item_fragment_serach_list_iv_gridview_two)
        ImageView ivBottom;
        @BindView(R.id.item_fragment_serach_list_tv_gridview_two)
        TextView tvBottom;
        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
