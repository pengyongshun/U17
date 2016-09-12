package com.example.u17.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.base_adapter.StatisticsMoreOtherAdapter;
import com.example.u17.base_uitls.log_uitls.LogUtils;
import com.example.u17.moudle_search.bean.StatisticsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StatisticsMoreOtherFragment extends Fragment {
    private Context context;
    @BindView(R.id.fragment_statistics_more_other_gv_content)
    public GridView mGridView;
    @BindView(R.id.fragment_statistics_more_other_tv_empty)
    public TextView tvEmpty;
    private List<StatisticsBean.OtherWorksBean> beens=new ArrayList<>();
    private StatisticsMoreOtherAdapter mStatisticsMoreOtherAdapter;
    private StatisticsBean mStatisticsBean;

    public static StatisticsMoreOtherFragment newInstance(Bundle bundle) {
        StatisticsMoreOtherFragment fragment = new StatisticsMoreOtherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_statistics_more_other, container, false);
        //从StatisticsMoreActivity传来的StatisticsBean
        Bundle bundle = getArguments();
        mStatisticsBean = (StatisticsBean) bundle.getSerializable("StatisticsBean");
        LogUtils.log(StatisticsMoreOtherFragment.class,mStatisticsBean.toString());
        initView(view);
        return view;
    }

    private void initView(View view) {
        ButterKnife.bind(this,view);
        mGridView.setEmptyView(tvEmpty);
        initAdapter();
        loadData();

    }

    private void loadData() {
        List<StatisticsBean.OtherWorksBean> otherWorks = mStatisticsBean.getOtherWorks();
        if (otherWorks==null){
            return;
        }
        beens.addAll(otherWorks);
        mStatisticsMoreOtherAdapter.notifyDataSetChanged();
    }
    private void initAdapter() {
        if (beens==null){
            return;
        }
        mStatisticsMoreOtherAdapter = new StatisticsMoreOtherAdapter(context,beens);
        mGridView.setAdapter(mStatisticsMoreOtherAdapter);
    }

}
