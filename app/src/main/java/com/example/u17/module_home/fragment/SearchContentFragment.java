package com.example.u17.module_home.fragment;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.u17.base_cusview.MyFlowLayout;
import com.example.u17.base_cusview.OnFlowLayoutListener;
import com.example.u17.fragment.BaseFragment;
import com.example.u17.module_home.adapter.MyCursorAdapter;
import com.example.u17.module_home.adapter.SearchHintAdapter;
import com.example.u17.module_home.bean.HotWord;
import com.example.u17.module_home.bean.InputText;
import com.example.u17.module_home.url.HomeUrl;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.google.gson.Gson;
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
 * Use the {@link SearchContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchContentFragment extends BaseFragment {
    @BindView(R.id.listview_search)
    ListView listView;
    View line;

    private MyFlowLayout myFlowLayout;
    private TextView tvClearHistory;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Context mContext;
    private List<HotWord.DataBean.ReturnDataBean> data;
    private List<InputText.DataBean.ReturnDataBean> hint;
    private SearchHintAdapter searchHintAdapter;

    private MyCursorAdapter mCursorAdapter;
    private String uriString = "content://com.example.u17.db.MyContentProvider";
    private Uri uri;

    public SearchContentFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchContentFragment.
     */
    public static SearchContentFragment newInstance(String param1, String param2) {
        SearchContentFragment fragment = new SearchContentFragment();
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
        View view = inflater.inflate(R.layout.fragment_search_content, container, false);
        ButterKnife.bind(this, view);
        initView();
        addFooter();
        uri = Uri.parse(uriString);
        hint = new ArrayList<>();
        searchHintAdapter = new SearchHintAdapter(mContext, hint);
        listView.setAdapter(searchHintAdapter);
        addHintText();
        addListener();
        return view;
    }

    //流式布局添加数据
    private void initView() {
        data = new ArrayList<>();
        OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HotWord hotWord = gson.fromJson(result, HotWord.class);
                if (hotWord != null && hotWord.getData().getReturnData() != null) {
                    data.addAll(hotWord.getData().getReturnData());
                    for (int i = 0; i < data.size(); i++) {
                        TextView textView = new TextView(mContext);
                        MyFlowLayout.LayoutParams layoutParams = new MyFlowLayout.LayoutParams(MyFlowLayout.LayoutParams.WRAP_CONTENT, MyFlowLayout.LayoutParams.WRAP_CONTENT);
                        textView.setLayoutParams(layoutParams);
                        textView.setBackgroundResource(R.drawable.shape_hot_word_bg);
                        textView.setTextColor(mContext.getResources().getColor(R.color.flowlayout_text_color_selector));
                        textView.setText(data.get(i).getTag());
                        myFlowLayout.addView(textView);
                    }
                }

            }
        }).start(HomeUrl.HOTWORD_PATH);
    }

    private void addFooter() {
        View footerView = LayoutInflater.from(mContext).inflate(R.layout.search_content_bottom, null);
        listView.addFooterView(footerView);
        myFlowLayout = (MyFlowLayout) footerView.findViewById(R.id.mfl_hot_word);
        tvClearHistory = (TextView) footerView.findViewById(R.id.tv_clear_history);
        line = footerView.findViewById(R.id.line_below);
    }

    //显示提示输入内容、显示历史记录
    private void addHintText() {
        String encode = null;
        try {
            encode = URLEncoder.encode(mParam1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (mParam2.equals("addToHistory")) {
            Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            mCursorAdapter = new MyCursorAdapter(mContext, cursor, true);
            listView.setAdapter(mCursorAdapter);
            addHistory(mParam1);
            listView.setVisibility(View.VISIBLE);
            tvClearHistory.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        } else if (mParam2.equals("showReminder")) {
            if (encode.length() > 0) {
                listView.setVisibility(View.VISIBLE);
                tvClearHistory.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                OkHttpTask.newInstance(mContext).enqueue(new IOkTaskCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        InputText text = gson.fromJson(result, InputText.class);
                        if (text != null && text.getData().getReturnData() != null) {
                            hint.addAll(text.getData().getReturnData());
                            searchHintAdapter.notifyDataSetChanged();
                        }
                    }
                }).start(HomeUrl.INPUTTEXT_PATH + encode + HomeUrl.FROM_PATH);
            }
        } else {
            Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            mCursorAdapter = new MyCursorAdapter(mContext, cursor, true);
            mCursorAdapter.changeCursor(cursor);
            listView.setAdapter(mCursorAdapter);
            if (!mCursorAdapter.isEmpty()) {
                listView.setVisibility(View.VISIBLE);
                tvClearHistory.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
            }
        }
    }

    private void addListener() {
        /**
         *  流布局监听
         */
        myFlowLayout.setOnFlowLayoutListener(new OnFlowLayoutListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textView = (TextView) view;
                String hotWord = textView.getText().toString();
                mListener.onFragmentInteraction(hotWord);
                //添加到历史记录
                Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
                mCursorAdapter = new MyCursorAdapter(mContext, cursor, true);
                listView.setAdapter(mCursorAdapter);
                addHistory(hotWord);
            }
        });
        /**
         * ListView 监听
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tv_hint);
                String content = textView.getText().toString();
                //点击提示内容跳到漫画详情页面
                if (mParam2.equals("showReminder")) {
                    //将所点击的内容添加到历史记录
                    Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
                    mCursorAdapter = new MyCursorAdapter(mContext, cursor, true);
                    listView.setAdapter(mCursorAdapter);
                    addHistory(content);
                    int comicId = searchHintAdapter.getData().get(position).getComic_id();
                    Intent intent = new Intent(mContext, StatisticsActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("comicId",comicId);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                } else {
                    //点击历史记录item
                    mListener.onFragmentInteraction(content);
                }

            }
        });
        /**
         * 清除历史记录监听
         */
        tvClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mContext.getContentResolver().delete(uri, null, null);
                Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
                mCursorAdapter.changeCursor(cursor);
                tvClearHistory.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
            }
        });
    }

    private void addHistory(String searchName) {
        if (mCursorAdapter.isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("search_name", searchName);
            mContext.getContentResolver().insert(uri, values);
            Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            mCursorAdapter.changeCursor(cursor);
        } else {
            Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            boolean flag = false;
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("search_name"));
                if (searchName.equals(name)) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                ContentValues values = new ContentValues();
                values.put("search_name", searchName);
                mContext.getContentResolver().insert(uri, values);
                Cursor cursor1 = mContext.getContentResolver().query(uri, null, null, null, null);
                mCursorAdapter.changeCursor(cursor1);
            }
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
