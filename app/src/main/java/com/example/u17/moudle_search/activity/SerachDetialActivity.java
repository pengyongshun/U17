package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.moudle_search.adapter.SerachDetialTabAdapter;
import com.example.u17.moudle_search.bean.ExtraBean;
import com.example.u17.moudle_search.bean.SerachListBean;
import com.example.u17.moudle_search.fragement.SerachDetialPopularityFragment;
import com.example.u17.moudle_search.fragement.SerachDetialTodayFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerachDetialActivity extends AppCompatActivity {
    @BindView(R.id.activity_serach_detial_tool)
    public Toolbar mToolbar;
    @BindView(R.id.activity_serach_detial_tool_tv_content)
    public TextView mTvTool;
    @BindView(R.id.activity_serach_detial_tab_title)
    public TabLayout mTabLayout;
    @BindView(R.id.activity_serach_detial_vp_fragment)
    public ViewPager mViewPager;
    private List<String> titles=new ArrayList<>();
    private List<Fragment> fragments=new ArrayList<>();
    private Bundle bundle;
    private ExtraBean extraBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_detial);
        ButterKnife.bind(this);
        //接收从搜索列表中获取的ExtraBean
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        extraBean = (ExtraBean) bundle.getSerializable("ExtraBean");
        if (extraBean==null){
            return;
        }
        String title = extraBean.getTitle();
        List<ExtraBean.TabListBean> tabList = extraBean.getTabList();
        int size = tabList.size();
        for (int i = 0; i < size; i++) {
            ExtraBean.TabListBean tabListBean = tabList.get(i);
            String tabTitle = tabListBean.getTabTitle();
            titles.add(tabTitle);
        }
        mTvTool.setText(title);
        setSupportActionBar(mToolbar);
        initFragment();
        initAdapter();
    }

    private void initAdapter() {
        SerachDetialTabAdapter serachDetialTabAdapter = new SerachDetialTabAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(serachDetialTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initFragment() {
        if (bundle==null){
            return;
        }
        SerachDetialTodayFragment serachDetialTodayFragment = new SerachDetialTodayFragment().newInstance(bundle);
        SerachDetialPopularityFragment serachDetialPopularityFragment = new SerachDetialPopularityFragment().newInstance(bundle);
        fragments.add(serachDetialTodayFragment);
        fragments.add(serachDetialPopularityFragment);
    }

    /**
     * 返回到上一个界面
     * */
    public void onClick(View view){
        finish();
    }
}
