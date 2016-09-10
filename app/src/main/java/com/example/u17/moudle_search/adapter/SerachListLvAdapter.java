package com.example.u17.moudle_search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.u17.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/8
 */
public class SerachListLvAdapter extends BaseAdapter {
    private List<Integer> idList;
    private Context context;
    private LayoutInflater mInflater;

    public SerachListLvAdapter(Context context, List<Integer> idList) {
        this.context = context;
        this.idList = idList;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return idList==null?0:idList.size();
    }

    @Override
    public Object getItem(int position) {
        return idList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler=null;
         if (convertView==null){
             convertView=mInflater.inflate(R.layout.item_fragment_serach_list_listview,parent,false);
             hodler=new ViewHodler(convertView);
         }else {
             hodler= (ViewHodler) convertView.getTag();
         }
        Integer id = idList.get(position);
        hodler.imageView.setImageResource(id);
        return convertView;
    }
    class ViewHodler{
        @BindView(R.id.item_fragment_serach_list_iv_listview)
        ImageView imageView;
        public ViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
