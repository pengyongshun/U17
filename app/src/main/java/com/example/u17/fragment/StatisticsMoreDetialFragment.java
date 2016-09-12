package com.example.u17.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.moudle_search.bean.StatisticsBean;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StatisticsMoreDetialFragment extends Fragment {
    @BindView(R.id.fragment_statistics_more_detial_tv_description)
    public TextView tvDescription;
    @BindView(R.id.fragment_statistics_more_detial_tv_lastweek)
    public TextView tvLastWeek;
    @BindView(R.id.fragment_statistics_more_detial_zp_tv_type_themids)
    public TextView tvThemIds;
    @BindView(R.id.fragment_statistics_more_detial_zp_tv_type_shortdesc)
    public TextView tvShortdesc;
    @BindView(R.id.fragment_statistics_more_detial_zp_tv_click)
    public TextView tvTotalClick;
    @BindView(R.id.fragment_statistics_more_detial_zp_tv_monthticke)
    public TextView tvMonthticke;
    @BindView(R.id.fragment_statistics_more_detial_zp_tv_favorite_total)
    public TextView tvFavoriteTotal;
    @BindView(R.id.fragment_statistics_more_detial_zp_tv_total_tucao)
    public TextView tvTotalTuCao;
    private Context context;
    private StatisticsBean mStatisticsBean;

    public static StatisticsMoreDetialFragment newInstance(Bundle bundle) {
        StatisticsMoreDetialFragment fragment = new StatisticsMoreDetialFragment();
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
        View view=inflater.inflate(R.layout.fragment_statistics_more_detial, container, false);
        //从StatisticsMoreActivity传来的StatisticsBean
        Bundle bundle = getArguments();
        mStatisticsBean = (StatisticsBean) bundle.getSerializable("StatisticsBean");
        initView(view);
        return view;
    }

    private void initView(View view) {
        ButterKnife.bind(this,view);
        loadData();
    }

    private void loadData() {
        if (mStatisticsBean==null){
            return;
        }
        String description = mStatisticsBean.getDescription();
        List<String> theme_ids = mStatisticsBean.getTheme_ids();
        String cate_id = mStatisticsBean.getCate_id();
        int favorite_total = mStatisticsBean.getFavorite_total();
        String last_update_week = mStatisticsBean.getLast_update_week();
        String month_ticket = mStatisticsBean.getMonth_ticket();
        double total_ticket = mStatisticsBean.getTotal_ticket();
        double total_tucao = mStatisticsBean.getTotal_tucao();
        tvDescription.setText(description);
        tvFavoriteTotal.setText("收藏："+favorite_total);
        tvLastWeek.setText("更新时间：周"+last_update_week);
        tvMonthticke.setText("月票："+month_ticket);
        tvTotalTuCao.setText("吐槽："+(total_tucao/10000)+"万");
        tvTotalClick.setText("点击："+(total_ticket/100000000)+"亿");
        tvShortdesc.setText("类型："+cate_id);
        int size = theme_ids.size();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < size; i++) {
            String themeid = theme_ids.get(i);
            stringBuilder.append(themeid).append("\t");
        }
        tvThemIds.setText("类别："+stringBuilder.toString());
    }

}
