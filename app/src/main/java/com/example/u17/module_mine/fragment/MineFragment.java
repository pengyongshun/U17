package com.example.u17.module_mine.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/8
 */
public class MineFragment extends Fragment implements View.OnClickListener{
    //用户头像
    @BindView(R.id.login_circle_header_image)
    public CircleImageView mCircleImageView;
    //登录入口
    @BindView(R.id.login_entrance)
    public TextView mLoginEntrance;
    //登录信息简介
    @BindView(R.id.login_entrance_info)
    public TextView getmLoginEntranceInfo;
    //设置
    @BindView(R.id.fragment_mine_setting)
    public ImageView mSetting;
    //消费记录
    @BindView(R.id.fragment_mine_text_account)
    //充值
    public TextView mAccount;
    @BindView(R.id.fragment_mine_text_recharge)
    public TextView mRecharge;
    //我的订阅
    @BindView(R.id.fragment_mine_text_subscribe)
    public TextView mSubscribe;
    //问题反馈
    @BindView(R.id.fragment_mine_text_feedback)
    public TextView mFeedback;
    //成为作者
    @BindView(R.id.fragment_mine_text_author)
    public TextView mAuthor;
    //签到
    @BindView(R.id.fragment_mine_text_sign)
    public TextView mSign;
    //游戏
    @BindView(R.id.fragment_mine_text_games)
    public TextView mGame;
    //关于我们
    @BindView(R.id.fragment_mine_text_about_us)
    public TextView mAboutUs;


    private Context context;
    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
