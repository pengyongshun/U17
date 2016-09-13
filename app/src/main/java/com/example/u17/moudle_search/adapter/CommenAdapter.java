package com.example.u17.moudle_search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.moudle_search.bean.CommentBean;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:pengyongshun
 * @Desc:
 * @Time:2016/9/13
 */
public class CommenAdapter extends BaseAdapter {
    List<CommentBean.DataBean.ReturnDataBean.CommentListBean> commentList;
    private LayoutInflater layoutInflater;
    private Context context;

    public CommenAdapter(Context context,  List<CommentBean.DataBean.ReturnDataBean.CommentListBean> commentList) {
        this.context = context;
        this.commentList = commentList;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return commentList==null?0:commentList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommenViewHodler hodler=null;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_activity_program_listview,parent,false);
            hodler=new CommenViewHodler(convertView);
        }else {
            hodler= (CommenViewHodler) convertView.getTag();
        }
        //更新UI
        CommentBean.DataBean.ReturnDataBean.CommentListBean commentListBean = commentList.get(position);
        String create_time_str = commentListBean.getCreate_time_str();
        String face = commentListBean.getFace();
        String nickname = commentListBean.getNickname();
        hodler.tvNickeName.setText(nickname);
        hodler.tvTime.setText(create_time_str);
        hodler.ivIcon.setImageResource(R.drawable.main_recycler_image_default);
        if (face==null){
            return null;
        }
        Picasso.with(context).load(face).into(hodler.ivIcon);
        return convertView;
    }
    class CommenViewHodler{
        @BindView(R.id.item_activity_program_iv_face)
        ImageView ivIcon;
        @BindView(R.id.item_activity_program_tv_nickname)
        TextView tvNickeName;
        @BindView(R.id.item_activity_program_tv_create_time_str)
        TextView tvTime;
        public CommenViewHodler(View view) {
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }
}
