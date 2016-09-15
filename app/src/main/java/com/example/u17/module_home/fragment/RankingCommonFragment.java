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
import android.widget.ProgressBar;

import com.example.u17.R;
import com.example.u17.fragment.BaseFragment;
import com.example.u17.module_home.adapter.RankingCommonAdapter;
import com.example.u17.module_home.bean.Comics;
import com.example.u17.module_home.url.HomeUrl;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.example.u17.simplecache.ACache;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankingCommonFragment extends BaseFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Context mContext;
    private List<Comics.DataBean.ReturnDataBean.ComicsBean> data;
    private RankingCommonAdapter rankingCommonAdapter;
    int page = 1;
    private ACache mCache;

    @BindView(R.id.ptrlv_ranking)
    PullToRefreshListView mListView;

    public RankingCommonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RankingCommonFragment.
     */
    public static RankingCommonFragment newInstance(String param1, String param2) {
        RankingCommonFragment fragment = new RankingCommonFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ranking_common, container, false);
        ButterKnife.bind(this, view);
        mCache = ACache.get(mContext);
        data = new ArrayList<>();
        rankingCommonAdapter = new RankingCommonAdapter(mContext, data, mParam2);
        mListView.setAdapter(rankingCommonAdapter);
        /***添加为空的视图****/
        ProgressBar progressBar = new ProgressBar(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        mListView.setEmptyView(progressBar);
        /***添加为空的视图****/
        setRefresh();
        initData();
        addListener();
        return view;
    }

    /**
     * 设置刷新
     */
    private void setRefresh() {
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout endLayout = mListView.getLoadingLayoutProxy(false, true);
        endLayout.setPullLabel("加载更多");
        endLayout.setRefreshingLabel("正在加载...");
        endLayout.setReleaseLabel("加载更多");
    }

    private void addListener() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                data.clear();
                OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject object = new JSONObject(result);
                            mCache.put(mParam1, object);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        handleResult(result);
                    }
                }).start(HomeUrl.RANKING_COMICS_PATH + mParam1 + "&page=" + page);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        handleResult(result);
                    }
                }).start(HomeUrl.RANKING_COMICS_PATH + mParam1 + "&page=" + page);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext,StatisticsActivity.class);
                Comics.DataBean.ReturnDataBean.ComicsBean comicsBean = rankingCommonAdapter.getData().get(position);
                int comicId = comicsBean.getComicId();
                intent.putExtra("comicId", comicId);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        JSONObject rankingCommonJsonObject = mCache.getAsJSONObject(mParam1);
        if (rankingCommonJsonObject != null) {
            handleResult(rankingCommonJsonObject.toString());
        } else {
            OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        mCache.put(mParam1, object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    handleResult(result);
                }
            }).start(HomeUrl.RANKING_COMICS_PATH + mParam1 + "&page=" + page);
        }
    }

    private void handleResult(String result) {
        Gson gson = new Gson();
        Comics comics = gson.fromJson(result, Comics.class);
        if (comics != null && comics.getData().getReturnData().getComics() != null) {
            List<Comics.DataBean.ReturnDataBean.ComicsBean> comicsBeanList = comics.getData().getReturnData().getComics();
            data.addAll(comicsBeanList);
            rankingCommonAdapter.notifyDataSetChanged();
            mListView.onRefreshComplete();
        }
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
