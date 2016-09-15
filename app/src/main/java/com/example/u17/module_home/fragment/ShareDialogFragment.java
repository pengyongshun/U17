package com.example.u17.module_home.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;

public class ShareDialogFragment extends DialogFragment implements View.OnClickListener{

    private ImageView weixinShare, friendsShare, qqShare, weiboShare, qqZoneShare;
    private TextView cancelShare;

    public static ShareDialogFragment newInstance() {
        ShareDialogFragment fragment = new ShareDialogFragment();
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //构造一个自定义style 的 dialog
        Dialog dialog = new Dialog(getActivity(), R.style.CustomDatePickerDialog);
        //设置为无标题
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.share_popup_window_view, null);
        weixinShare = (ImageView) view.findViewById(R.id.iv_share_weixin);
        friendsShare = (ImageView) view.findViewById(R.id.iv_share_friends);
        qqShare = (ImageView) view.findViewById(R.id.iv_share_qq);
        weiboShare = (ImageView) view.findViewById(R.id.iv_share_weibo);
        qqZoneShare = (ImageView) view.findViewById(R.id.iv_share_qq_zone);
        cancelShare = (TextView) view.findViewById(R.id.tv_cancel_share);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.ranking_listview_item_scale);
        weixinShare.startAnimation(animation);
        friendsShare.startAnimation(animation);
        qqShare.startAnimation(animation);
        weiboShare.startAnimation(animation);
        qqZoneShare.startAnimation(animation);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = 400;
        window.setAttributes(layoutParams);
        addListener();
        return dialog;
    }

    private void addListener() {
        cancelShare.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel_share:
                this.dismiss();
                //Toast.makeText(getActivity(),"取消",Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
