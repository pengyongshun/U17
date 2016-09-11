package com.example.u17.module_bookrack.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.u17.R;
import com.example.u17.module_bookrack.adapter.BookrackPageAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BookrackFragment extends Fragment{

    private List<Fragment> fragments;

    private BookrackPageAdapter pagerAdapter;

    @BindView(R.id.tab_layout_bookshelf)
    TabLayout mBookshelfTab;
    @BindView(R.id.view_pager_bookshelf)
    ViewPager mViewPager;
    @BindView(R.id.iv_delete_read_history)
    ImageView mDelete;


    private Context context;

    public static BookrackFragment newInstance() {
        BookrackFragment fragment = new BookrackFragment();
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
        View view = inflater.inflate(R.layout.fragment_bookrack, container, false);
        ButterKnife.bind(this, view);
        fragments = new ArrayList<>();
        pagerAdapter = new BookrackPageAdapter(getFragmentManager(),context,fragments);
        mViewPager.setAdapter(pagerAdapter);
        mBookshelfTab.setupWithViewPager(mViewPager);
        initFragments();
        return view;
    }

    private void initFragments() {
        fragments.add(BookrackCollectionFragment.newInstance());
        fragments.add(BookrackHistoryFragment.newInstance());
        fragments.add(BookrackHistoryFragment.newInstance());
        pagerAdapter.notifyDataSetChanged();
    }
}
