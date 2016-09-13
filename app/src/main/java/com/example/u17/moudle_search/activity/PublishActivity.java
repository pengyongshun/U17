package com.example.u17.moudle_search.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.u17.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishActivity extends AppCompatActivity {
    @BindView(R.id.activity_pulish_tool)
    public Toolbar toolbar;
    @BindView(R.id.activity_pulish_tool_et)
    public EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.activity_pulish_tool_tv_chance:
                finish();
                break;
            case R.id.activity_pulish_tool_iv_pulish:
                String content = editText.getEditableText().toString();
                if (content.isEmpty()){
                    Toast.makeText(PublishActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(PublishActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
