package com.example.u17;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.u17.module_bookrack.fragment.BookrackFragment;
import com.example.u17.module_home.fragment.HomeFragment;
import com.example.u17.module_mine.fragment.MineFragment;
import com.example.u17.module_serch.fragment.SerchFragment;

import java.util.ArrayList;
import java.util.List;

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
    private List<Fragment> fragments=new ArrayList<>();
    private HomeFragment mHomeFragment;
    private SerchFragment mSerchFragment;
    private MineFragment mMineFragment;
    private BookrackFragment mBookrackFragment;
    private Fragment curFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mManager = getSupportFragmentManager();
        iniFragment();
    }

    private void iniFragment() {
        mHomeFragment = new HomeFragment().newInstance();
        mSerchFragment = new SerchFragment().newInstance().newInstance();
        mMineFragment = new MineFragment().newInstance().newInstance();
        mBookrackFragment = new BookrackFragment().newInstance().newInstance();
        fragments.add(mHomeFragment);
        fragments.add(mSerchFragment);
        fragments.add(mBookrackFragment);
        fragments.add(mMineFragment);
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
            transaction.add(R.id.activity_home_fl_fragment,fragment);
        }else {
            transaction.show(fragment);
        }
        transaction.commit();
        curFragment=fragment;
    }
}
