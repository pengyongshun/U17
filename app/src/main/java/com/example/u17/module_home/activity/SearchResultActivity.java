package com.example.u17.module_home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.adapter.SearchResultAdapter;
import com.example.u17.module_home.bean.SearchResult;
import com.example.u17.moudle_search.activity.StatisticsActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchResultActivity extends AppCompatActivity {
    private static final String arg1 = "http://app.u17.com/v3/appV3/android/phone/list/commonComicList?argValue=";
    private static final String arg2 = "&argName=";
    private static final String arg3 = "&argCon=";
    private static final String arg4 = "&page=";
    private static final String arg5 = "&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia";
    private List<SearchResult.DataBean.ReturnDataBean.ComicsBean> data;
    private SearchResultAdapter adapter;
    private int page = 1;
    private String[] arrays;
    private String argValue;
    private String argName;
    private String argCon;
    boolean flag;
    @BindView(R.id.spinner_search_result_sort)
    Spinner spinner;
    @BindView(R.id.ptrf_search_result)
    PullToRefreshListView refreshListView;
    @BindView(R.id.tv_search_result_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tvTitle.setText(title);
        argValue = intent.getStringExtra("argValue");
        argName = intent.getStringExtra("argName");
        flag = intent.getBooleanExtra("IsFromSearch",false);
        data = new ArrayList<>();
        addRefresh();
    }

    private void initData(final String item,int page) {
        OkHttpTask.newInstance(SearchResultActivity.this).enqueue(new IOkTaskCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                SearchResult searchResult = gson.fromJson(result, SearchResult.class);
                if (searchResult != null && searchResult.getData().getReturnData().getComics() != null){
                    data.addAll(searchResult.getData().getReturnData().getComics());
                    adapter = new SearchResultAdapter(SearchResultActivity.this, data,item);
                    refreshListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    refreshListView.onRefreshComplete();
                }
            }
        }).start(arg1+argValue+arg2+argName+arg3+item+arg4+page+arg5);
    }

    private void addRefresh() {
        arrays = getResources().getStringArray(R.array.search_result_sort);
        //自定义Spinner样式
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item,arrays);
        arrayAdapter.setDropDownViewResource(R.layout.custom_dropdown_stytle);
        spinner.setAdapter(arrayAdapter);
        if(argValue.equals("2") && flag == false){
            argCon = "1";
            spinner.setVisibility(View.INVISIBLE);
            initData(argCon,page);
        }else if(argValue.equals("14") && flag == false){
            argCon = "vip";
            spinner.setVisibility(View.INVISIBLE);
            initData(argCon,page);
        }else if ((argValue.equals("12") ||argValue.equals("0") || argValue.equals("2")) && flag == false){
            argCon = "2";
            spinner.setVisibility(View.INVISIBLE);
            initData(argCon,page);
        }else{

            //设置监听Spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                    argCon = String.valueOf(position + 1);
                    initData(argCon,page);
                    refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
                    refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                        @Override
                        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                            data.clear();
                            initData(argCon,page);

                        }
                        @Override
                        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                            page++;
                            initData(argCon,page);
                        }
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner.setSelection(1,true);
        }

        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int comicId = adapter.getData().get(position - 1).getComicId();
                Intent intent = new Intent(SearchResultActivity.this, StatisticsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("comicId",comicId);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });

    }
    @OnClick(R.id.iv_search_result_back)
    public void click(View view){
        onBackPressed();
    }
}
