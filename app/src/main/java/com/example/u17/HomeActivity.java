package com.example.u17;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.u17.module_bookrack.fragment.BookrackFragment;
import com.example.u17.module_home.fragment.HomeFragment;
import com.example.u17.module_mine.fragment.MineFragment;
import com.example.u17.module_serch.fragment.SerchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.activity_home_rg_radiobutton)
    public RadioGroup mRadioGroup;
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
        initListener();
    }

    private void initListener() {
      mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, int checkedId) {
              switch (checkedId){
                  case R.id.activity_home_rb_bookself:
                      seclectStateView(0);
                      //切换到书库fragment
                      switchFragment(0);
                      break;
                  case R.id.activity_home_rb_mine:
                      seclectStateView(1);
                      //切换到我的fragment
                      switchFragment(1);
                      break;
                  case R.id.activity_home_rb_serch:
                      seclectStateView(2);
                      //切换到搜索fragment
                      switchFragment(2);
                      break;
                  case R.id.activity_home_rb_home:
                      seclectStateView(3);
                      //切换到首页fragment
                      switchFragment(3);
                      break;
              }
          }
      });
    }

    private void seclectStateView(int index) {
        int childCount = mRadioGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            RadioButton view = (RadioButton) mRadioGroup.getChildAt(i);
            view.setChecked(false);
        }
        RadioButton curView = (RadioButton) mRadioGroup.getChildAt(index);
        curView.setChecked(true);
    }

    private void iniFragment(){
        mHomeFragment = new HomeFragment().newInstance();
        mSerchFragment = new SerchFragment().newInstance().newInstance();
        mMineFragment = new MineFragment().newInstance().newInstance();
        mBookrackFragment = new BookrackFragment().newInstance().newInstance();
        fragments.add(mHomeFragment);
        fragments.add(mSerchFragment);
        fragments.add(mBookrackFragment);
        fragments.add(mMineFragment);
        switchFragment(0);
        seclectStateView(0);

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
