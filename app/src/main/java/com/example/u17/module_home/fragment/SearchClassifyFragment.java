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
import android.widget.GridView;

import com.example.u17.R;
import com.example.u17.fragment.BaseFragment;
import com.example.u17.module_home.activity.SearchResultActivity;
import com.example.u17.module_home.adapter.SearchClassifyAdapter;
import com.example.u17.module_home.bean.BoutiqueClassify;
import com.example.u17.module_home.url.HomeUrl;
import com.google.gson.Gson;
import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchClassifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchClassifyFragment extends BaseFragment {
    private Context mContent;
    private List<BoutiqueClassify.DataBean.ReturnDataBean.RankinglistBean> data;
    private SearchClassifyAdapter adapter;
    private GridView gridView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchClassifyFragment() {
    }


    public static SearchClassifyFragment newInstance(String param1, String param2) {
        SearchClassifyFragment fragment = new SearchClassifyFragment();
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
        mContent = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_classify, container, false);
        gridView = (GridView) view.findViewById(R.id.gv_classify);
        data = new ArrayList<>();
        adapter = new SearchClassifyAdapter(mContent, data);
        gridView.setAdapter(adapter);
        initData();
        return view;
    }

    private void initData() {
        OkHttpTask.newInstance(mContent).enqueue(new IOkTaskCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                BoutiqueClassify classify = gson.fromJson(result, BoutiqueClassify.class);
                if (classify != null && classify.getData().getReturnData().getRankinglist() != null){
                    data.addAll(classify.getData().getReturnData().getRankinglist());
                    adapter.notifyDataSetChanged();
                }
            }
        }).start(HomeUrl.CLASSIFY_PATH);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String argValue = data.get(position).getArgValue();
                String title = data.get(position).getIconSortName();
                String argName = data.get(position).getArgName();
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("argValue",argValue);
                intent.putExtra("argName",argName);
                intent.putExtra("title",title);
                intent.putExtra("IsFromSearch",true);
                startActivity(intent);
            }
        });
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
