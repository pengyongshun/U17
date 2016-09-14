package com.example.u17.moudle_search.fragement;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.example.u17.moudle_search.adapter.SerachDetialTodayAdapter;
import com.example.u17.moudle_search.ascytask.SerachDetialHeadTodayAscytask;
import com.example.u17.moudle_search.bean.ExtraBean;
import com.example.u17.moudle_search.bean.SerachDetialTodayBean;
import com.example.u17.moudle_search.url.SerachUrl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SerachDetialTodayFragment extends Fragment {
    private Context context;
    @BindView(R.id.serach_detial_today_prlv)
    public PullToRefreshListView mListView;
    @BindView(R.id.serach_detial_today_tv_empty)
    public TextView mTvEmpty;
    private List<SerachDetialTodayBean>beens=new ArrayList<>();
    private SerachDetialTodayAdapter mAdapter;
    private Bundle bundle;
    private int count=2;
    private int argCon;
    private String argName;
    private int argValue;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    LoadData(1);
                    mListView.onRefreshComplete();
                    break;
                case 2:
                    LoadData(count);
                    mListView.onRefreshComplete();
                    break;
            }
        }
    };

    public static SerachDetialTodayFragment newInstance(Bundle bundle) {
        SerachDetialTodayFragment fragment = new SerachDetialTodayFragment();
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
        View view=inflater.inflate(R.layout.fragment_serach_detial_today, container, false);
        ButterKnife.bind(this,view);
        mListView.setEmptyView(mTvEmpty);
        bundle = getArguments();
        if (bundle.isEmpty()){
            return null;
        }
        ExtraBean extraBean = (ExtraBean) bundle.getSerializable("ExtraBean");
        List<ExtraBean.TabListBean> tabList = extraBean.getTabList();
        ExtraBean.TabListBean tabListBean = tabList.get(0);
         argCon = tabListBean.getArgCon();
         argName = tabListBean.getArgName();
         argValue = tabListBean.getArgValue();
        initAdapter();
        LoadData(1);
        initListener();
        return view;
    }

    private void initListener() {
        //同时支持刷新和加载更多功能
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                if (!beens.isEmpty()){
                    beens.clear();
                }
                mHandler.sendEmptyMessageDelayed(1,1000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉加载更多
                count++;
                mHandler.sendEmptyMessageDelayed(2,1000);
            }
        });
        //点击每一个item转跳到统计的界面
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将comicId和cover传过去
                if (beens==null){
                    return;
                }
                SerachDetialTodayBean comicsBean = beens.get(position-1);
                int comicId = comicsBean.getComicId();
                String cover = comicsBean.getCover();
                Intent intent=new Intent(context, StatisticsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("comicId",comicId);
                bundle.putString("cover",cover);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
    }

    private void LoadData(int page) {
        new SerachDetialHeadTodayAscytask(new SerachDetialHeadTodayAscytask.SDHTCallBack() {
            @Override
            public void callBack(List<SerachDetialTodayBean> serachDetialTodayBean) {
                 if (serachDetialTodayBean==null){
                     return;
                 }
                beens.addAll(serachDetialTodayBean);
                mAdapter.notifyDataSetChanged();
            }
        }).execute(SerachUrl.SERACH_DETIAL_HEAD_URL_BASE+"?argValue="+argValue+"&argName="+argName+"&argCon="
        +argCon+"&page="+page+SerachUrl.SERACH_DETIAL_HEAD_URL_BOTTOM);
    }

    private void initAdapter() {
        if (beens!=null){
            mAdapter = new SerachDetialTodayAdapter(context,beens);
            mListView.setAdapter(mAdapter);
        }

    }

}
