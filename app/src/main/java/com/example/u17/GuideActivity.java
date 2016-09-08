package com.example.u17;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.u17.base_adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {
    @BindView(R.id.activity_guide_vp_guide)
    public ViewPager mViewPager;
    @BindView(R.id.activity_guide_ll_indactor)
    public LinearLayout mLLIndactor;
    private List<View> viewList=new ArrayList<>();
    private int mChildCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        loadData();
        initAdapter();
        initListener();
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initIndactor(position);
                if (position==3){
                    View view = viewList.get(position);
                    Button btnEnter = (Button) view.findViewById(R.id.item_activity_guide_btn_enter);
                    btnEnter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(GuideActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initAdapter() {
        GuideAdapter guideAdapter = new GuideAdapter(viewList);
        mViewPager.setAdapter(guideAdapter);
    }

    /**
     * 加载数据
     * */
    private void loadData() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.item_activity_guide_vp_one, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_activity_guide_vp_two, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.item_activity_guide_vp_three, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.item_activity_guide_vp_four, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
         mChildCount = mLLIndactor.getChildCount();
        initIndactor(0);

    }
    private void initIndactor(int index){
        for (int i = 0; i < mChildCount; i++) {
            ImageView indactorView = (ImageView) mLLIndactor.getChildAt(i);
            indactorView.setEnabled(false);
        }
        ImageView fristView = (ImageView) mLLIndactor.getChildAt(index);
        fristView.setEnabled(true);

    }
}
