package com.example.u17.module_home.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.fragment.ShareDialogFragment;
import com.example.u17.module_home.fragment.SpecialDetailFragment;
import com.example.u17.module_home.fragment.SpecialOtherFragment;
import com.example.u17.module_home.url.HomeUrl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpecialDetailActivity extends AppCompatActivity {

    private static final String TAG = "SpecialDetailActivity";
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private Fragment mCurrentFragment;
    private SpecialDetailFragment detailFragment;
    private SpecialOtherFragment otherFragment;

    @BindView(R.id.tv_special_detail_title)
    TextView mSpecialTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_detail);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        String title = intent.getStringExtra("title");
        mSpecialTitle.setText(title);
        switch (type){
            case 2:
                String otherUrl = intent.getStringExtra("url");
                otherFragment = SpecialOtherFragment.newInstance(otherUrl,"");
                controlFragment(otherFragment,"other");
                break;
            case 5:
                int specialId = intent.getIntExtra("specialId", 0);
                String detailUrl = HomeUrl.SPECIAL_DETAIL_PATH+specialId;
                detailFragment = SpecialDetailFragment.newInstance(detailUrl,"");
                controlFragment(detailFragment,"detail");
                break;
        }

    }

    @OnClick({R.id.iv_special_detail_back,R.id.iv_special_detail_share})
    public void click(View view ){
        switch (view.getId()){
            case R.id.iv_special_detail_back:
                onBackPressed();
                break;
            case R.id.iv_special_detail_share:
                ShareDialogFragment dialog = new ShareDialogFragment();
                dialog.show(getSupportFragmentManager(),"dialog");
                break;
        }
    }

    private void controlFragment(Fragment fragment, String name) {
        transaction = fragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(R.id.fl_special_detail, fragment, name);
        }
        transaction.commit();
        mCurrentFragment = fragment;
    }
}
