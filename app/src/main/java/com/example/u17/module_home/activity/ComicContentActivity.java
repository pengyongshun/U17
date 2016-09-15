package com.example.u17.module_home.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u17.R;
import com.example.u17.base_cusview.XCDanmuView;
import com.example.u17.module_home.adapter.ComicContentAdapter;
import com.example.u17.module_home.bean.ComicContent;
import com.example.u17.module_home.bean.TuCao;
import com.example.u17.module_home.url.HomeUrl;
import com.example.u17.module_login.activity.LoginActivity;
import com.example.u17.simplecache.ACache;
import com.google.gson.Gson;
import com.xinying.httplibrary.IOkTaskCallBack;
import com.xinying.httplibrary.OkHttpTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicContentActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isShow = false;
    private boolean isSee = false;
    private boolean isBottom;
    private PopupWindow title;
    private PopupWindow menu;
    private PopupWindow vent;

    private TextView mTitle;
    private int position;
    private int comicId;
    private ComicContentAdapter adapter;
    private List<ComicContent.DataBean.ReturnDataBean.ImageListBean> data;
    private List<ComicContent.DataBean.ReturnDataBean.ImageListBean> perChapterData;
    private ACache mCache;
    private Map<Integer, List<ComicContent.DataBean.ReturnDataBean.ImageListBean>> map = new HashMap<>();
    private TextView tvScreen, tvLight, tvCateLog, tvSetting;
    private EditText editText;
    private TextView tvStart;
    private TextView showProgress;
    private SeekBar seekBar;
    private List<String> tuCaoData;
    private ImageView ivStart;

    @BindView(R.id.comic_content_listview)
    ListView listView;
    @BindView(R.id.tv_vent_count)
    TextView tvVentCount;
    @BindView(R.id.ll_vent)
    LinearLayout linearLayout;
    @BindView(R.id.danmu)
    XCDanmuView mDanmuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_content);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_comic_content);
        ButterKnife.bind(this);
        mCache = ACache.get(this);
        addPopWindow();
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        comicId = intent.getIntExtra("comicId", 0);
        String chapterTitle = intent.getStringExtra("chapterTitle");
        mTitle.setText(chapterTitle);
        tuCaoData = new ArrayList<>();
        data = new ArrayList<>();
        perChapterData = new ArrayList<>();
        perChapterData.clear();
        tuCaoData.clear();
        adapter = new ComicContentAdapter(ComicContentActivity.this, perChapterData);
        listView.setAdapter(adapter);
        /***添加为空的视图****/
        ProgressBar progressBar = new ProgressBar(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        progressBar.setLayoutParams(params);
        listView.setEmptyView(progressBar);
        /***添加为空的视图****/
        initData();

        addListener();
    }

    private void addPopWindow() {
        //载入PopupWindow的布局
        View popTitleView = getLayoutInflater().inflate(R.layout.comic_content_title, null, false);
        mTitle = (TextView) popTitleView.findViewById(R.id.tv_chapter_title);
        ImageView ivBack = (ImageView) popTitleView.findViewById(R.id.iv_comic_content_back);
        View popMenuView = getLayoutInflater().inflate(R.layout.comic_content_menu, null, false);
        showProgress = (TextView) popMenuView.findViewById(R.id.tv_show_progress);
        seekBar = (SeekBar) popMenuView.findViewById(R.id.seekbar);
        tvScreen = (TextView) popMenuView.findViewById(R.id.tv_reader_menu_screen);
        tvLight = (TextView) popMenuView.findViewById(R.id.tv_reader_menu_light);
        tvCateLog = (TextView) popMenuView.findViewById(R.id.tv_reader_menu_catalog);
        tvSetting = (TextView) popMenuView.findViewById(R.id.tv_reader_menu_setting);
        View tucaoPopWindow = getLayoutInflater().inflate(R.layout.comic_content_vent, null, false);
        editText = (EditText) tucaoPopWindow.findViewById(R.id.et_vent_in);
        tvStart = (TextView) tucaoPopWindow.findViewById(R.id.tv_start_vent);
        ivStart = (ImageView) tucaoPopWindow.findViewById(R.id.iv_vent_off_or_on);
        menu = new PopupWindow(popMenuView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        title = new PopupWindow(popTitleView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        vent = new PopupWindow(tucaoPopWindow, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (content.length() > 0) {
                    Intent intent = new Intent();
                    intent.setClass(ComicContentActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "请填写吐槽内容!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvScreen.setOnClickListener(this);
        tvLight.setOnClickListener(this);
        tvCateLog.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void addListener() {
        WindowManager manager = this.getWindowManager();
        final int width = manager.getDefaultDisplay().getWidth();
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vent.setWidth(width);
                vent.setFocusable(true);
                vent.setBackgroundDrawable(new BitmapDrawable());
                vent.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
                vent.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                vent.showAtLocation(v, Gravity.BOTTOM, 0, 0); //设置menu位置在底部
                vent.setOutsideTouchable(true);
                vent.update();
                isSee = true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isSee == true) {
                    vent.dismiss();
                    isSee = false;
                } else {
                    if (isShow == true) {
                        title.setAnimationStyle(R.anim.popwindow_title_out);
                        title.dismiss();
                        menu.setAnimationStyle(R.anim.popwindow_menu_out);
                        menu.dismiss();
                        isShow = false;
                    } else {
                        menu.setWidth(width);
                        menu.setAnimationStyle(R.anim.popwindow_menu_in);
                        menu.showAtLocation(parent, Gravity.BOTTOM, 0, 0); //设置menu位置在底部
                        menu.setFocusable(false);
                        menu.setOutsideTouchable(true);
                        menu.update();
                        title.setWidth(width);
                        title.setAnimationStyle(R.anim.popwindow_title_in);
                        title.showAtLocation(parent, Gravity.TOP, 0, 0); //设置title位置在顶部
                        title.setFocusable(false);
                        title.setOutsideTouchable(true);
                        title.update();
                        isShow = true;
                    }
                }
            }
        });
        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDanmuView.isWorking()) {
                    mDanmuView.hide();
                    ivStart.setImageResource(R.drawable.icon_vent_off);
                } else {
                    mDanmuView.start();
                    ivStart.setImageResource(R.drawable.icon_vent_on);
                }
            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                         @Override
                                         public void onScrollStateChanged(AbsListView view, int scrollState) {

                                             switch (scrollState) {
                                                 // 当不滚动时
                                                 case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                                                     //滑到底部
                                                     if (isBottom) {
                                                         if (position == (map.keySet().size() - 1)) {
                                                             Toast.makeText(getApplicationContext(), "已经是最后一章了！", Toast.LENGTH_SHORT).show();
                                                         } else {
                                                             position++;
                                                             List<ComicContent.DataBean.ReturnDataBean.ImageListBean> imageListBeen = map.get(position);
                                                             perChapterData.addAll(imageListBeen);
                                                             adapter.notifyDataSetChanged();
                                                         }
                                                     }
                                                     //滑到顶部
                                                     if (listView.getFirstVisiblePosition() == 0) {
                                                         if (position != 0) {
                                                             perChapterData.clear();
                                                             List<ComicContent.DataBean.ReturnDataBean.ImageListBean> imageListBeen = map.get(position - 1);
                                                             List<ComicContent.DataBean.ReturnDataBean.ImageListBean> imageListBeen1 = map.get(position);
                                                             perChapterData.addAll(imageListBeen);
                                                             perChapterData.addAll(imageListBeen1);
                                                             adapter.notifyDataSetChanged();
                                                             listView.setSelection(imageListBeen.size() - 1);
                                                             position--;
                                                         }

                                                     }
                                                     break;
                                             }
                                         }

                                         @Override
                                         public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                                              int totalItemCount) {
                                             isBottom = (firstVisibleItem + visibleItemCount) == totalItemCount;
                                         }
                                     }

        );


    }

    private void initData() {
        JSONObject comicContentJsonObject = mCache.getAsJSONObject("comicContentJson" + comicId);
        if (comicContentJsonObject != null) {
            handleContentResult(comicContentJsonObject.toString());
        } else {
            OkHttpTask.newInstance(this).enqueue(new IOkTaskCallBack() {
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        mCache.put("comicContentJson" + comicId, object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    handleContentResult(result);
                }
            }).start(HomeUrl.COMIC_CONTENT_PATH + comicId);
        }

        OkHttpTask.newInstance(this).enqueue(new IOkTaskCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                TuCao tuCao = gson.fromJson(result, TuCao.class);
                if (tuCao != null && tuCao.getData().getReturnData() != null) {
                    List<TuCao.DataBean.ReturnDataBean> returnData = tuCao.getData().getReturnData();
                    for (int i = 0; i < returnData.size(); i++) {
                        String content = returnData.get(i).getContent();
                        tuCaoData.add(content);
                    }
                    mDanmuView.initDanmuItemViews(tuCaoData);
                }
            }
        }).start(HomeUrl.TUCAO_PATH + comicId);
    }

    private void handleContentResult(String result) {
        Gson gson = new Gson();
        ComicContent content = gson.fromJson(result, ComicContent.class);
        if (content != null && content.getData().getReturnData() != null) {
            List<ComicContent.DataBean.ReturnDataBean> returnData = content.getData().getReturnData();
            for (int i = 0; i < returnData.size(); i++) {
                List<ComicContent.DataBean.ReturnDataBean.ImageListBean> chapterData = returnData.get(i).getImage_list();
                map.put(i, chapterData);
                data.addAll(chapterData);
            }

            List<ComicContent.DataBean.ReturnDataBean.ImageListBean> imageListBeen = map.get(position);
            perChapterData.addAll(imageListBeen);
            adapter.notifyDataSetChanged();
            showProgress.setText("1/" + perChapterData.size());

            tvCateLog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(ComicContentActivity.this, AllCateLogActivity.class);
                    intent.putExtra("comicId", comicId);
                    startActivity(intent);
                    title.dismiss();
                    menu.dismiss();
                    isShow = false;
                    finish();
                }
            });
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_reader_menu_screen:

                break;
            case R.id.tv_reader_menu_light:

                break;

            case R.id.tv_reader_menu_setting:

                break;
        }
    }
}
