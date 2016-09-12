package com.example.u17.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.u17.R;

public class StatisticsMoreDetialFragment extends Fragment {
    private Context context;
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
        return view;
    }

}
