package com.example.u17.module_bookrack.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.u17.R;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/12
 */
public class BookrackHistoryFragment extends Fragment{
    private Context context;

    public static BookrackHistoryFragment newInstance() {
        BookrackHistoryFragment fragment = new BookrackHistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bookrack_history, container, false);
        return view;
    }
}
