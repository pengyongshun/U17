package com.example.u17.module_home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.bean.InputText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 时光彼岸之外 on 2016/6/17.
 */
public class SearchHintAdapter extends AbsBaseAdapter<InputText.DataBean.ReturnDataBean> {
    public SearchHintAdapter(Context context, List<InputText.DataBean.ReturnDataBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.search_text_item_view,null);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(data.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        @BindView(R.id.tv_hint)
        TextView text;
        public ViewHolder(View view) {
            view.setTag(this);
            ButterKnife.bind(this,view);
        }
    }
}
