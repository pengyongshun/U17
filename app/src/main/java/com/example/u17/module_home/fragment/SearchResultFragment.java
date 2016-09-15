package com.example.u17.module_home.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.fragment.BaseFragment;
import com.example.u17.module_home.adapter.WordSearchResultAdapter;
import com.example.u17.module_home.bean.WordSearchResult;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    public static final String arg1 = "http://app.u17.com/v3/appV3/android/phone/search/searchResult?q=";
    public static final String arg2 = "&page=";
    public static final String arg3 = "&android_id=3000048383665174&v=3070099&model=GT-P5210&come_from=wandoujia";
    private List<WordSearchResult.DataBean.ReturnDataBean.ComicsBean> data;
    private WordSearchResultAdapter adapter;
    @BindView(R.id.ptrf_search_result)
    PullToRefreshListView refreshListView;
    private ListView mListView;
    private int page = 1;
    private TextView tvComicCount;
    private String encode = null;

    public SearchResultFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchResultFragment.
     */
    public static SearchResultFragment newInstance(String param1, String param2) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this,view);
        data = new ArrayList<>();
        adapter = new WordSearchResultAdapter(mContext, data);
        refreshListView.setAdapter(adapter);
        initView();
        addListener();
        addHeadAndFooter();
        return view;
    }

    private void initView() {
        try {
            encode = URLEncoder.encode(mParam1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                WordSearchResult searchResult = gson.fromJson(result, WordSearchResult.class);
                if (searchResult != null && searchResult.getData().getReturnData().getComics() != null){
                    List<WordSearchResult.DataBean.ReturnDataBean.ComicsBean> comics = searchResult.getData().getReturnData().getComics();
                    data.addAll(comics);
                    int count = searchResult.getData().getReturnData().getComicNum();
                    tvComicCount.setText("找到与“"+mParam1+"”有关的漫画"+count+"本");
                    adapter.notifyDataSetChanged();
                }
            }
        }).start(arg1+encode+arg2+page+arg3);
    }

    private void addListener() {
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                data.clear();
                OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        WordSearchResult searchResult = gson.fromJson(result, WordSearchResult.class);
                        if(searchResult != null && searchResult.getData().getReturnData().getComics() != null){
                            data.addAll(searchResult.getData().getReturnData().getComics());
                            adapter.notifyDataSetChanged();
                            refreshListView.onRefreshComplete();
                        }
                    }
                }).start(arg1+encode+arg2+page+arg3);
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        WordSearchResult searchResult = gson.fromJson(result, WordSearchResult.class);
                        if(searchResult != null && searchResult.getData().getReturnData().getComics() != null){
                            data.addAll(searchResult.getData().getReturnData().getComics());
                            adapter.notifyDataSetChanged();
                            refreshListView.onRefreshComplete();
                        }
                    }
                }).start(arg1+encode+arg2+page+arg3);
            }
        });
        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int comicId = adapter.getData().get(position -2).getComicId();
                Intent intent = new Intent(mContext, StatisticsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("comicId",comicId);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }
    private void addHeadAndFooter(){
        mListView = refreshListView.getRefreshableView();
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.search_result_header, null);
        tvComicCount = (TextView) headerView.findViewById(R.id.tv_show_comic_count);
        mListView.addHeaderView(headerView);
        View footerView = LayoutInflater.from(mContext).inflate(R.layout.reminder_bottom_view, null);
        mListView.addFooterView(footerView);
    }
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
