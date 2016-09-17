package com.example.u17.module_mine.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_login.activity.LoginActivity;
import com.example.u17.module_mine.activity.AboutUsActivity;
import com.example.u17.module_mine.activity.AuthorActivity;
import com.example.u17.module_mine.activity.FeedbackActivity;
import com.example.u17.module_mine.activity.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/8
 */
public class LoginMineFragment extends Fragment implements View.OnClickListener {
    //用户头像
    @BindView(R.id.login2_circle_header_image)
    public CircleImageView mCircleImageView;
    //登录入口
    @BindView(R.id.login2_entrance)
    public TextView mLoginEntrance;
    //登录信息简介
    @BindView(R.id.login2_entrance_info)
    public TextView mLoginEntranceInfo;
    //设置
    @BindView(R.id.fragment2_mine_setting)
    public ImageView mSetting;
    //消费记录
    @BindView(R.id.fragment2_mine_text_account)
    //充值
    public TextView mAccount;
    @BindView(R.id.fragment2_mine_text_recharge)
    public TextView mRecharge;
    //我的订阅
    @BindView(R.id.fragment2_mine_text_subscribe)
    public TextView mSubscribe;
    //问题反馈
    @BindView(R.id.fragment2_mine_text_feedback)
    public TextView mFeedback;
    //成为作者
    @BindView(R.id.fragment2_mine_text_author)
    public TextView mAuthor;
    //签到
    @BindView(R.id.fragment2_mine_text_sign)
    public TextView mSign;
    //游戏
//    @BindView(R.id.fragment2_mine_text_games)
//    public TextView mGame;
    //关于我们
    @BindView(R.id.fragment2_mine_text_about_us)
    public TextView mAboutUs;


    private Context context;

    public static LoginMineFragment newInstance() {
        LoginMineFragment fragment = new LoginMineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine2, container, false);
        ButterKnife.bind(this, view);
        initOnClick();
        return view;
    }

    private void initOnClick() {
        mCircleImageView.setOnClickListener(this);
//        mLoginEntrance.setOnClickListener(this);

        boolean state = LoginActivity.isLogin;
        if (state == true) {
            mLoginEntrance.setText("King1033");
            mLoginEntrance.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        mLoginEntranceInfo.setTextColor(getResources().getColor(R.color.colorPrimary));
        mLoginEntranceInfo.setText("VIP0");

        mSetting.setOnClickListener(this);
        mAccount.setOnClickListener(this);
        mRecharge.setOnClickListener(this);
        mSubscribe.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
        mAuthor.setOnClickListener(this);
        mSign.setOnClickListener(this);
//        mGame.setOnClickListener(this);
        mAboutUs.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login2_circle_header_image:
                ////////////签到/////////////////
                Intent intent1 = new Intent(context, LoginActivity.class);
                startActivity(intent1);
                break;
//            case R.id.login2_entrance:
//                ////////////签到/////////////////
//                Intent intent2=new Intent(context, LoginActivity.class);
//                startActivity(intent2);
//                break;
            case R.id.fragment2_mine_setting:
                Intent intent3 = new Intent(context, SettingActivity.class);
                startActivity(intent3);
                break;
            case R.id.fragment2_mine_text_account:
                ////////////签到/////////////////
                Intent intent4 = new Intent(context, LoginActivity.class);
                startActivity(intent4);
                break;
            case R.id.fragment2_mine_text_recharge:
                ////////////签到/////////////////
                Intent intent5 = new Intent(context, LoginActivity.class);
                startActivity(intent5);
                break;
            case R.id.fragment2_mine_text_subscribe:
                ////////////签到/////////////////
                Intent intent6 = new Intent(context, LoginActivity.class);
                startActivity(intent6);
                break;
            case R.id.fragment2_mine_text_feedback:
                Intent intent7 = new Intent(context, FeedbackActivity.class);
                startActivity(intent7);
                break;
            case R.id.fragment2_mine_text_author:
                Intent intent8 = new Intent(context, AuthorActivity.class);
                startActivity(intent8);
                break;
            case R.id.fragment2_mine_text_sign:
                ////////////签到/////////////////
                Intent intent9 = new Intent(context, LoginActivity.class);
                startActivity(intent9);
                break;
//            case R.id.fragment2_mine_text_games:
//                Intent intent10=new Intent(context, GameActivity.class);
//                startActivity(intent10);
//                break;
            case R.id.fragment2_mine_text_about_us:
                Intent intent11 = new Intent(context, AboutUsActivity.class);
                startActivity(intent11);
                break;
        }
    }
}
