package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.base_adapter.StatisticsMoreAdapter;
import com.example.u17.fragment.StatisticsMoreDetialFragment;
import com.example.u17.fragment.StatisticsMoreOtherFragment;
import com.example.u17.moudle_search.bean.StatisticsBean;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class StatisticsMoreActivity extends AppCompatActivity {
    @BindView(R.id.activity_statistics_more_iv_back)
    public ImageView ivBack;
    @BindView(R.id.activity_statistics_more_civ_icon)
    public CircleImageView mCircleImageView;
    @BindView(R.id.activity_statistics_more_tv_name)
    public TextView mTvName;
    @BindView(R.id.activity_statistics_more_tab_title)
    public TabLayout mTabLayout;
    @BindView(R.id.activity_statistics_more_vp_title)
    public ViewPager mViewPager;
    private List<Fragment>fragmentList=new ArrayList<>();
    private List<String> titleList=new ArrayList<>();
    private StatisticsBean statisticsBean;
    private Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_more);
        ButterKnife.bind(this);
        //从StatisticsAcyivity中传来数据
        Intent intent = getIntent();
         bundle = intent.getBundleExtra("bundle");
        statisticsBean = (StatisticsBean) bundle.getSerializable("StatisticsBean");
        String avatar = statisticsBean.getAvatar();
        String name = statisticsBean.getName();
        Picasso.with(this).load(avatar).into(mCircleImageView);
        mTvName.setText(name);
        initFragment();
        iniAdapter();
    }

    private void iniAdapter() {
        StatisticsMoreAdapter statisticsMoreAdapter = new StatisticsMoreAdapter(getSupportFragmentManager(), fragmentList, titleList);
        mViewPager.setAdapter(statisticsMoreAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initFragment() {
        if (bundle==null){
            return;
        }
        StatisticsMoreDetialFragment statisticsMoreDetialFragment = new StatisticsMoreDetialFragment().newInstance(bundle);
        StatisticsMoreOtherFragment statisticsMoreOtherFragment = new StatisticsMoreOtherFragment().newInstance(bundle);
        fragmentList.add(statisticsMoreDetialFragment);
        fragmentList.add(statisticsMoreOtherFragment);
        titleList.add("漫画详情");
        titleList.add("其他作品");
    }
    public void onClick(View view){
        finish();
    }
}
