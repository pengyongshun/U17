package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.u17.R;
import com.example.u17.base_adapter.ReadingAdapter;
import com.example.u17.base_http.BaseUrl;
import com.example.u17.moudle_search.ascytask.ReadingAscytask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCategoryItemActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_allcategroy_lv_content)
    public ListView mListView;
    @BindView(R.id.activity_allcategroy_tool_content)
    public Toolbar mToolbar;
    private List<String> urlList=new ArrayList<>();
    private ReadingAdapter mReadingAdapter;
    private String chapter_id;
    private PopupWindow sharePopupWindow;
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category_item);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();
        //TODO 从AllCategoryActivity中获取chater_id
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        chapter_id = bundle.getString("chapter_id");
        iniAdapter();
        loadData();
    }
    private void loadData() {
        if (chapter_id==null){
            return;
        }
        new ReadingAscytask(new ReadingAscytask.ReadingCallBack() {
            @Override
            public void callBack(List<String> urls) {
                if (urls==null){
                    return;
                }
                urlList.addAll(urls);
                mReadingAdapter.notifyDataSetChanged();
            }
        }).execute(BaseUrl.READING_BASE_URL+chapter_id+BaseUrl.READING_BOTTOM_URL);
    }

    private void iniAdapter() {
        if (urlList==null){
            return;
        }
        mReadingAdapter = new ReadingAdapter(this,urlList);
        mListView.setAdapter(mReadingAdapter);
    }

 public void onClick(View view){
     switch (view.getId()){
         case R.id.activity_allcategroy_tool_iv_back:
             finish();
             break;
         case R.id.activity_allcategroy_tool_iv_share:
             CreateSharePupawindow();
             break;
         case R.id.iv_share_friends:
             sharePopupWindow.dismiss();
             Toast.makeText(AllCategoryItemActivity.this, "分享到朋友圈", Toast.LENGTH_SHORT).show();
             break;
         case R.id.iv_share_qq:
             sharePopupWindow.dismiss();
             Toast.makeText(AllCategoryItemActivity.this, "分享到qq", Toast.LENGTH_SHORT).show();
             break;
         case R.id.iv_share_weibo:
             sharePopupWindow.dismiss();
             Toast.makeText(AllCategoryItemActivity.this, "分享到微博", Toast.LENGTH_SHORT).show();
             break;
         case R.id.iv_share_weixin:
             sharePopupWindow.dismiss();
             Toast.makeText(AllCategoryItemActivity.this, "分享到微信", Toast.LENGTH_SHORT).show();
             break;
         case R.id.iv_share_qq_zone:
             sharePopupWindow.dismiss();
             Toast.makeText(AllCategoryItemActivity.this, "分享到qq空间", Toast.LENGTH_SHORT).show();
             break;
     }
 }
    private void CreateSharePupawindow(){
        View view= LayoutInflater.from(this).inflate(R.layout.share_popup_window_view,null);
        ImageView imagewx = (ImageView) view.findViewById(R.id.iv_share_weixin);
        ImageView imageqq = (ImageView) view.findViewById(R.id.iv_share_qq);
        ImageView imagefriends = (ImageView) view.findViewById(R.id.iv_share_friends);
        ImageView imagewb = (ImageView) view.findViewById(R.id.iv_share_weibo);
        ImageView imageqqz = (ImageView) view.findViewById(R.id.iv_share_qq_zone);
        sharePopupWindow=new PopupWindow(view,width,400);
        sharePopupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
        imagefriends.setOnClickListener(this);
        imagewx.setOnClickListener(this);
        imageqq.setOnClickListener(this);
        imagewb.setOnClickListener(this);
        imageqqz.setOnClickListener(this);
    }

}
