package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.base_uitls.log_uitls.LogUtils;
import com.example.u17.moudle_search.bean.RankingListBean;
import com.example.u17.moudle_search.bean.SerachListBean;
import com.example.u17.moudle_search.fragement.SerachDetialCategoryFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerachDetialCategoryActivity extends AppCompatActivity {
    @BindView(R.id.activity_serach_detial_category_tool)
    Toolbar mToolbar;
    @BindView(R.id.activity_serach_detial_category_tool_sn_meun)
    public Spinner mSpinner;
    @BindView(R.id.activity_serach_detial_category_tool_tv_content)
    public TextView mTextView;
    private Bundle bundle;
    private List<Fragment> fragments=new ArrayList<>();
    private FragmentManager mManager;
    private Fragment curFragment;
    private RankingListBean rankingListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_detial_category);
        ButterKnife.bind(this);
        mManager = getSupportFragmentManager();
        //TODO 获取从SerachFragment中传来的RankingListBean
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        rankingListBean = (RankingListBean) bundle.getSerializable("RankingListBean");
        rankingListBean.setArgCon(1);
        String sortName = rankingListBean.getSortName();
        mTextView.setText(sortName);
        setSupportActionBar(mToolbar);
        initFragment();
        initSpinner();
    }

    private void initFragment() {
        if (bundle==null){
            return;
        }
        SerachDetialCategoryFragment fragment1 = new SerachDetialCategoryFragment().newInstance(bundle);
        SerachDetialCategoryFragment fragment2 = new SerachDetialCategoryFragment().newInstance(bundle);
        SerachDetialCategoryFragment fragment3 = new SerachDetialCategoryFragment().newInstance(bundle);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        switchFragment(0);
    }
    /***
     * fragment 为要切换的Fragment
     * curFragment 为当前显示的Fragment
     * 注意：在切换时，要隐藏当前的Fragment，显示即将要切换的Fragment、需要对其进行判断是否被添加
     * **/
    private void switchFragment(int index) {
        FragmentTransaction transaction = mManager.beginTransaction();
        Fragment fragment = fragments.get(index);
        if (curFragment!=null&&curFragment.isAdded()){
            transaction.hide(curFragment);
        }
        if (!fragment.isAdded()){
            transaction.add(R.id.activity_serach_detial_category_fl_fragment,fragment);
        }else {
            transaction.show(fragment);
        }
        transaction.commit();
        curFragment=fragment;
    }

    private void initSpinner() {
        List<String> meuns=new ArrayList<>();
        meuns.add("点击");
        meuns.add("更新");
        meuns.add("收藏");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, meuns);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //点击  切换到点击的fragment
                        rankingListBean.setArgCon(1);
                        switchFragment(0);
                        break;
                    case 1:
                        rankingListBean.setArgCon(2);
                        switchFragment(1);
                        //更新  切换到更新的fragment
                        break;
                    case 2:
                        rankingListBean.setArgCon(3);
                        switchFragment(2);
                        //收藏  切换到收藏的fragment
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClick(View view){
        //返回到上一个界面
        finish();
    }
}
