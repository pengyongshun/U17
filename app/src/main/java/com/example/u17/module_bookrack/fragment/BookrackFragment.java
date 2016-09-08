package com.example.u17.module_bookrack.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.u17.R;

public class BookrackFragment extends Fragment {
    private Context context;
    public static BookrackFragment newInstance(String param1, String param2) {
        BookrackFragment fragment = new BookrackFragment();
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
        View view=inflater.inflate(R.layout.fragment_bookrack, container, false);
        return view;
    }

}
