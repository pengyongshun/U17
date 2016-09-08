package com.example.u17;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.activity_home_iv_bookself)
    public ImageView mBtnBookself;
    @BindView(R.id.activity_home_iv_home)
    public ImageView mBtnHome;
    @BindView(R.id.activity_home_iv_serch)
    public ImageView mBtnSerch;
    @BindView(R.id.activity_home_iv_mine)
    public ImageView mBtnMine;
    @BindView(R.id.activity_home_fl_fragment)
    public FrameLayout mFrameLayout;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mManager = getSupportFragmentManager();
    }
}
