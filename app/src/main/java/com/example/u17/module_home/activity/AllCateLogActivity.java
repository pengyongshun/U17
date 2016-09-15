package com.example.u17.module_home.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_home.adapter.TotalChapterAdapter;
import com.example.u17.module_home.bean.ComicDetail;
import com.example.u17.module_home.url.HomeUrl;
import com.example.u17.module_login.activity.LoginActivity;
import com.example.u17.simplecache.ACache;
import com.google.gson.Gson;
import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllCateLogActivity extends AppCompatActivity {
    @BindView(R.id.gv_chapter)
    GridView gridView;
    @BindView(R.id.total_chapter)
    TextView totalChapter;
    private int comicId;
    private List<ComicDetail.DataBean.ReturnDataBean.ChapterListBean> data;
    private TotalChapterAdapter adapter;
    private ACache mCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cate_log);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN,WindowManager.LayoutParams. FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_all_cate_log);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        Intent intent = getIntent();
        comicId = intent.getIntExtra("comicId",0);
        initData();
    }
    private void initData() {
        data = new ArrayList<>();
        adapter = new TotalChapterAdapter(this,data);
        JSONObject comicDetailJsonObject = mCache.getAsJSONObject("comicDetailJson" + comicId);
        if (comicDetailJsonObject != null) {
            handleComicResult(comicDetailJsonObject.toString());
        } else {
            OkHttpTask.newInstance(this).enqueue(new IOkTaskCallBack() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        mCache.put("comicDetailJson" + comicId, object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    handleComicResult(result);
                }
            }).start(HomeUrl.COMIC_DETAIL_PATH + comicId);
        }}

    private void handleComicResult(String result) {
        Gson gson = new Gson();
        ComicDetail comicDetail = gson.fromJson(result, ComicDetail.class);
        if (comicDetail != null && comicDetail.getData().getReturnData().getComic() != null) {
            data.addAll(comicDetail.getData().getReturnData().getChapter_list());
            createComicCatalog(data);
            adapter.notifyDataSetChanged();
        }
    }

    private void createComicCatalog(final List<ComicDetail.DataBean.ReturnDataBean.ChapterListBean> chapter_list){
        int size = chapter_list.size();
        totalChapter.setText("全部共："+size+"话");
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int type = chapter_list.get(position).getType();
                if (type == 2) {
                    Intent intent = new Intent(AllCateLogActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if (type == 3) {
                    Intent intent = new Intent(AllCateLogActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(AllCateLogActivity.this, ComicContentActivity.class);
                    String chapterTitle = chapter_list.get(position).getName();
                    intent.putExtra("chapterTitle",chapterTitle);
                    intent.putExtra("position", position);
                    intent.putExtra("comicId", comicId);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @OnClick(R.id.iv_comic_content_back)
    public void click(View view){
        onBackPressed();
    }
}
