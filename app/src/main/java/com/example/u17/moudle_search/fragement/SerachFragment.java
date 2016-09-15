package com.example.u17.moudle_search.fragement;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.base_uitls.log_uitls.LogUtils;
import com.example.u17.module_home.activity.SearchActivity;
import com.example.u17.moudle_search.activity.SerachDetialActivity;
import com.example.u17.moudle_search.activity.SerachDetialCategoryActivity;
import com.example.u17.moudle_search.activity.SerachSerachActivity;
import com.example.u17.moudle_search.adapter.SerachListGvBottomAdapter;
import com.example.u17.moudle_search.adapter.SerachListGvHeadAdapter;
import com.example.u17.moudle_search.adapter.SerachListLvAdapter;
import com.example.u17.moudle_search.bean.ExtraBean;
import com.example.u17.moudle_search.bean.SerachListBean;
import com.example.u17.moudle_search.http.HttpSerachUitls;
import com.example.u17.moudle_search.url.SerachUrl;
import com.example.u17.moudle_search.bean.RankingListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerachFragment extends Fragment implements View.OnClickListener {
    private Context context;
    @BindView(R.id.fragment_serach_list_lv_title)
    public ListView mListView;
    @BindView(R.id.fragment_serach_list_btn_serach)
    public Button mBtnSerach;
    private List<Integer> ids=new ArrayList<>();
    private SerachListLvAdapter mSerachListLvAdapter;
    private List<SerachListBean.DataBean.ReturnDataBean.TopListBean>topBeans=new ArrayList<>();
    private SerachListGvHeadAdapter mSerachListGvHeadAdapter;
    private View mHeadView;
    private GridView mGvHead;
    private List<SerachListBean.DataBean.ReturnDataBean.RankingListBean>rankingBeans=new ArrayList<>();
    private View mBottomView;
    private GridView mGvBottom;
    private SerachListGvBottomAdapter mSerachListGvBottomAdapter;


    public static SerachFragment newInstance() {
        SerachFragment fragment = new SerachFragment();
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
        View view=inflater.inflate(R.layout.fragment_serach, container, false);
        ButterKnife.bind(this,view);
        initListview();
        iniHeadView();
        initBottomView();
        mBtnSerach.setOnClickListener(this);
        return view;
    }

    private void initBottomView() {
        mBottomView = LayoutInflater.from(context).inflate(R.layout.fragment_serach_list_bottom,null);
        mGvBottom = (GridView) mBottomView.findViewById(R.id.fragment_serach_list_gv_bottom);
        initBottomAdapter();
        loadBottomData();
        initBottomListener();
    }

    private void initBottomListener() {
        mGvBottom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(context, SerachDetialCategoryActivity.class);
                Bundle bundle=new Bundle();
                if (!rankingBeans.isEmpty()){
                    RankingListBean rankingListBean=new RankingListBean();
                    SerachListBean.DataBean.ReturnDataBean.RankingListBean rankingBean = rankingBeans.get(position);
                    String argName = rankingBean.getArgName();
                    int argValue = rankingBean.getArgValue();
                    String sortName = rankingBean.getSortName();
                    rankingListBean.setArgValue(argValue);
                    rankingListBean.setArgName(argName);
                    rankingListBean.setSortName(sortName);
                    LogUtils.log(SerachFragment.class,rankingListBean.toString());
                    bundle.putSerializable("RankingListBean",rankingListBean);
                    intent1.putExtra("bundle",bundle);
                    startActivity(intent1);
                }
            }
        });
    }
    private void loadBottomData() {
        HttpSerachUitls.init(SerachUrl.SERACH_LIST_URL_BASE).getSerachListBeanFromNet().enqueue(new Callback<SerachListBean>() {
            @Override
            public void onResponse(Call<SerachListBean> call, Response<SerachListBean> response) {
                if (response==null){
                    return;
                }
                SerachListBean bean = response.body();
                SerachListBean.DataBean data = bean.getData();
                SerachListBean.DataBean.ReturnDataBean returnData = data.getReturnData();
                List<SerachListBean.DataBean.ReturnDataBean.RankingListBean> rankingList = returnData.getRankingList();
                rankingBeans.addAll(rankingList);
                mSerachListGvBottomAdapter.notifyDataSetChanged();
                //添加头部
                mListView.addFooterView(mBottomView);
                mSerachListLvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SerachListBean> call, Throwable t) {

            }
        });
    }

    private void initBottomAdapter() {
        if (rankingBeans!=null){
            mSerachListGvBottomAdapter = new SerachListGvBottomAdapter(context,rankingBeans);
            mGvBottom.setAdapter(mSerachListGvBottomAdapter);
        }
    }


    private void iniHeadView() {
        mHeadView = LayoutInflater.from(context).inflate(R.layout.fragment_serach_list_head,null);
        mGvHead = (GridView) mHeadView.findViewById(R.id.fragment_serach_list_gv_head);
        loadHeadData();
        iniHeadListener();
    }

    private void iniHeadListener() {
       mGvHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               ExtraBean extraBean=new ExtraBean();
               Intent intent=new Intent(context, SerachDetialActivity.class);
               Bundle bundle=new Bundle();
               if (!topBeans.isEmpty()){
                   SerachListBean.DataBean.ReturnDataBean.TopListBean topListBean = topBeans.get(position);
                   SerachListBean.DataBean.ReturnDataBean.TopListBean.ExtraBean extra = topListBean.getExtra();
                   String title = extra.getTitle();
                   extraBean.setTitle(title);
                   List<ExtraBean.TabListBean> beenList=new ArrayList<ExtraBean.TabListBean>();
                   List<SerachListBean.DataBean.ReturnDataBean.TopListBean.ExtraBean.TabListBean> tabList = extra.getTabList();
                   int size = tabList.size();
                   for (int i = 0; i < size; i++) {
                       ExtraBean.TabListBean tabListBean1=new ExtraBean.TabListBean();
                       SerachListBean.DataBean.ReturnDataBean.TopListBean.ExtraBean.TabListBean tabListBean = tabList.get(i);
                       int argCon = tabListBean.getArgCon();
                       int argValue = tabListBean.getArgValue();
                       String argName = tabListBean.getArgName();
                       String tabTitle = tabListBean.getTabTitle();
                       tabListBean1.setArgCon(argCon);
                       tabListBean1.setArgName(argName);
                       tabListBean1.setTabTitle(tabTitle);
                       tabListBean1.setArgValue(argValue);
                       beenList.add(tabListBean1);
                   }
                   extraBean.setTabList(beenList);
                   bundle.putSerializable("ExtraBean",extraBean);
                   intent.putExtra("bundle",bundle);
                   startActivity(intent);
               }

           }
       });
    }

    private void loadHeadData() {
        HttpSerachUitls.init(SerachUrl.SERACH_LIST_URL_BASE).getSerachListBeanFromNet().enqueue(new Callback<SerachListBean>() {
            @Override
            public void onResponse(Call<SerachListBean> call, Response<SerachListBean> response) {
                if (response==null){
                    return;
                }
                SerachListBean bean = response.body();
                SerachListBean.DataBean data = bean.getData();
                SerachListBean.DataBean.ReturnDataBean mReturnData = data.getReturnData();
                List<SerachListBean.DataBean.ReturnDataBean.TopListBean> topList = mReturnData.getTopList();
                topBeans.addAll(topList);
                initHeadAdapter();
                //添加头部
                mListView.addHeaderView(mHeadView);
                mSerachListLvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SerachListBean> call, Throwable t) {

            }
        });
    }


    private void initHeadAdapter() {
        if (!topBeans.isEmpty()){
            mSerachListGvHeadAdapter = new SerachListGvHeadAdapter(context,topBeans);
            mGvHead.setAdapter(mSerachListGvHeadAdapter);
        }
    }

    private void initListview() {
        //添加空试图进度条
        ProgressBar progressBar = new ProgressBar(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        ids.add(R.drawable.icon_classify_bottom_top_image);
        mSerachListLvAdapter = new SerachListLvAdapter(context,ids);
        mListView.setAdapter(mSerachListLvAdapter);
        mListView.setEmptyView(progressBar);
    }
    /**
     * 点击转跳到搜索界面
     *
     * */
    public void onClick(View view){
        Intent intent=new Intent(context, SearchActivity.class);
        startActivity(intent);
    }

}
