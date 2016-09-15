package com.example.u17.module_home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.u17.R;
import com.example.u17.module_home.adapter.SpecialAdapter;
import com.example.u17.module_home.bean.BoutiqueSpecial;
import com.example.u17.simplecache.ACache;
import com.google.gson.Gson;
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
import butterknife.OnClick;

public class SpecialActivity extends AppCompatActivity {
    @BindView(R.id.spinner_special_title)
    Spinner spinner;
    @BindView(R.id.ptrf_special)
    PullToRefreshListView refreshListView;
    private ListView mListView;
    private String[] arrays;
    private List<BoutiqueSpecial.DataBean.ReturnDataBean.ComicsBean> data;
    private SpecialAdapter adapter;
    private static final String arg1 = "http://app.u17.com/v3/appV3/android/phone/comic/special?argCon=";
    private static final String arg2 = "&page=1&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia";
    private ACache mCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        data = new ArrayList<>();
        adapter = new SpecialAdapter(SpecialActivity.this, data);
        refreshListView.setAdapter(adapter);
        initData();
        addListener();
        mListView = refreshListView.getRefreshableView();
        View footerView = LayoutInflater.from(this).inflate(R.layout.reminder_bottom_view, null);
        mListView.addFooterView(footerView);
        /***添加为空的视图****/
        View emptyView = LayoutInflater.from(this).inflate(R.layout.activity_show_error_empty, null);
        refreshListView.setEmptyView(emptyView);
        /***添加为空的视图****/
    }

    private void addListener() {
        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BoutiqueSpecial.DataBean.ReturnDataBean.ComicsBean comicsBean = adapter.getData().get(position-1);
                String title = comicsBean.getTitle();
                int specialType = comicsBean.getSpecialType();
                Intent intent = new Intent();
                switch (specialType){
                    case 1:
                        int specialId = comicsBean.getSpecialId();
                        intent.setClass(SpecialActivity.this, SpecialDetailActivity.class);
                        intent.putExtra("type",5);
                        intent.putExtra("specialId",specialId);
                        intent.putExtra("title",title);
                        break;
                    case 2:
                        String url = comicsBean.getUrl();
                        intent.setClass(SpecialActivity.this, SpecialDetailActivity.class);
                        intent.putExtra("type",2);
                        intent.putExtra("url",url);
                        intent.putExtra("title",title);
                        break;
                }
                startActivity(intent);
            }
        });
    }


    private void initData() {
        arrays = getResources().getStringArray(R.array.special);
        //自定义Spinner样式
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item,arrays);
        arrayAdapter.setDropDownViewResource(R.layout.custom_dropdown_stytle);
        spinner.setAdapter(arrayAdapter);
        //设置监听Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long arg3) {
                data.clear();
                JSONObject specialJsonObject = mCache.getAsJSONObject("special"+(position+1));
                if (specialJsonObject != null) {
                    handleResult(specialJsonObject.toString());
                } else {
                    OkHttpTask.newInstance(SpecialActivity.this).enqueue(new IOkTaskCallBack() {
                        @Override
                        public void onSuccess(String result) {
                            try {
                                JSONObject object = new JSONObject(result);
                                mCache.put("special"+(position+1), object);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            handleResult(result);
                        }
                    }).start(arg1 + (position + 1) + arg2);
                }
                refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                        data.clear();
                        OkHttpTask.newInstance(SpecialActivity.this).enqueue(new IOkTaskCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                try {
                                    JSONObject object = new JSONObject(result);
                                    mCache.put("special"+(position+1), object);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                handleResult(result);
                                refreshListView.onRefreshComplete();
                            }
                        }).start(arg1 + (position + 1) + arg2);
                    }

                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
    }

    private void handleResult(String result) {
        Gson gson = new Gson();
        BoutiqueSpecial special = gson.fromJson(result, BoutiqueSpecial.class);
        if (special!=null&&special.getData().getReturnData().getComics()!=null) {
            data.addAll(special.getData().getReturnData().getComics());
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.iv_special_back)
    public void click(View view){
        onBackPressed();
    }
}
