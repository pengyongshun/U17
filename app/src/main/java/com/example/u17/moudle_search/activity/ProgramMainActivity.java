package com.example.u17.moudle_search.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.base_http.BaseUrl;
import com.example.u17.module_login.activity.LoginActivity;
import com.example.u17.moudle_search.adapter.CommenAdapter;
import com.example.u17.moudle_search.ascytask.CommenAscyTask;
import com.example.u17.moudle_search.bean.CommentBean;
import com.example.u17.moudle_search.url.SerachUrl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramMainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.activity_program_tool_actionbar)
    public Toolbar toolbar;
    @BindView(R.id.activity_program_lv_list)
    public ListView mListView;
    @BindView(R.id.activity_program_ll_write_comment)
    public LinearLayout llWriteComment;
    @BindView(R.id.activity_program_tv_show_comment)
    public TextView tvShowComment;
    private List<CommentBean.DataBean.ReturnDataBean.CommentListBean> beens=new ArrayList<>();
    private CommenAdapter mCommenAdapter;
    private String id;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        //从上一个界面接收id
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        id = bundle.getString("id");
        initAdapter();
        loadData();
    }

    private void loadData() {
        if (id==null){
            return;
        }
        new CommenAscyTask(new CommenAscyTask.CommenCallBack() {
            @Override
            public void callBack(CommentBean commentBean) {
               if (commentBean==null){
                   return;
               }
                List<CommentBean.DataBean.ReturnDataBean.CommentListBean> commentList = commentBean.getData().getReturnData().getCommentList();
                beens.addAll(commentList);
                mCommenAdapter.notifyDataSetChanged();
            }
        }).execute(BaseUrl.COMMEN_BASE_URL+id+BaseUrl.COMMEN_BOTTOM_URL);
    }

    private void initAdapter() {
        if (beens==null){
            return;
        }
        mCommenAdapter = new CommenAdapter(this,beens);
        mListView.setAdapter(mCommenAdapter);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_program_ll_write_comment:
                CreatePupawindow();
                break;
            case R.id.activity_program_tool_back:
                if (popupWindow!=null){
                    popupWindow.dismiss();
                }
                finish();
                break;
            case R.id.program_popuwindow_btn_nicke:
                //转跳到发布界面
                Intent intent1=new Intent(ProgramMainActivity.this,PublishActivity.class);
                startActivity(intent1);
                break;
            case R.id.program_popuwindow_btn_login:
                //转跳到登录界面
                popupWindow.dismiss();
                Intent intent=new Intent(ProgramMainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void CreatePupawindow(){
       View view= LayoutInflater.from(this).inflate(R.layout.program_popuwindow,null);
        Button btnNicke = (Button) view.findViewById(R.id.program_popuwindow_btn_nicke);
        Button btnLogin = (Button) view.findViewById(R.id.program_popuwindow_btn_login);
        popupWindow=new PopupWindow(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        btnLogin.setOnClickListener(this);
        btnNicke.setOnClickListener(this);
    }
}
