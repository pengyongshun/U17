package com.example.u17.module_home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.u17.R;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.boutique_refresh_expand_listview)
    public PullToRefreshExpandableListView mExpandListView;
    private Context mContext;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        iniData();
        initProgress();
        return view;
    }

    private void iniData() {

    }

    /**
     * 添加为空的进度
     *
     * **/
    private void initProgress() {
        ProgressBar progressBar = new ProgressBar(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        mExpandListView.setEmptyView(progressBar);
    }

}
