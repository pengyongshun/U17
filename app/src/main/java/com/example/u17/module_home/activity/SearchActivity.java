package com.example.u17.module_home.activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.u17.R;
import com.example.u17.base_cusview.MyEditText;
import com.example.u17.fragment.BaseFragment;
import com.example.u17.module_home.fragment.SearchClassifyFragment;
import com.example.u17.module_home.fragment.SearchContentFragment;
import com.example.u17.module_home.fragment.SearchResultFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, BaseFragment.OnFragmentInteractionListener{
    private FragmentManager manager;
    private Fragment oldFragment;
    private FragmentTransaction transaction;
    private SearchClassifyFragment searchClassifyFragment;
    private SearchContentFragment searchContentFragment;
    private SearchResultFragment searchResultFragment;
    private int flag = 0;
    private String action = "showHistory";

    @BindView(R.id.et_search)
    MyEditText mEditText;
    @BindView(R.id.iv_classify_search)
    ImageView ivSearch;
    @BindView(R.id.ll_search)
    LinearLayout linearLayout;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchClassifyFragment = new SearchClassifyFragment();
        searchContentFragment = new SearchContentFragment();
        searchResultFragment = new SearchResultFragment();
        manager = getSupportFragmentManager();

        transaction = manager.beginTransaction();
        searchClassifyFragment = SearchClassifyFragment.newInstance("", "");
        controlFragment(searchClassifyFragment);
        transaction.commit();

        linearLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        flag = 1;
        transaction = manager.beginTransaction();
        searchContentFragment = SearchContentFragment.newInstance("", action);
        controlFragment(searchContentFragment);
        transaction.commit();
        linearLayout.setVisibility(View.GONE);
        mEditText.setVisibility(View.VISIBLE);
        ivSearch.setVisibility(View.VISIBLE);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                content = replaceBlank(mEditText.getText().toString());
                if(content.equals("")){
                    action = "showHistory";
                }else{
                    action = "showReminder";
                }
                transaction = manager.beginTransaction();
                searchContentFragment = SearchContentFragment.newInstance(content, action);
                controlFragment(searchContentFragment);
                transaction.commit();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_classify_back, R.id.iv_classify_search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_classify_back:
                if (flag == 1) {
                    transaction = manager.beginTransaction();
                    searchClassifyFragment = SearchClassifyFragment.newInstance("", "");
                    controlFragment(searchClassifyFragment);
                    transaction.commit();
                    ivSearch.setVisibility(View.GONE);
                    mEditText.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                    flag = 0;
                } else if (flag == 2) {
                    content = replaceBlank(mEditText.getText().toString());
                    transaction = manager.beginTransaction();
                    searchContentFragment = SearchContentFragment.newInstance(content, action);
                    controlFragment(searchContentFragment);
                    transaction.commit();
                    mEditText.setFocusableInTouchMode(true);
                    mEditText.setFocusable(true);
                    ivSearch.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    onBackPressed();
                }
                break;
            case R.id.iv_classify_search:
                content = replaceBlank(mEditText.getText().toString());
                if ((content).equals("")) {
                    Toast.makeText(getApplicationContext(), "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    action = "addToHistory";
                    flag = 2;
                    ivSearch.setVisibility(View.GONE);
                    transaction = manager.beginTransaction();
                    searchResultFragment = SearchResultFragment.newInstance(content, "");
                    controlFragment(searchResultFragment);
                    transaction.commit();
                    mEditText.setFocusableInTouchMode(false);
                    mEditText.setFocusable(false);
                    mEditText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mEditText.setFocusableInTouchMode(true);
                            mEditText.setFocusable(true);
                            ivSearch.setVisibility(View.VISIBLE);
                        }
                    });
                }
                break;
        }

    }
    //过滤搜索内容
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    private void controlFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (oldFragment != null) {
            fragmentTransaction.hide(oldFragment);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.ll_content, fragment);
        }
        fragmentTransaction.commit();
        oldFragment = fragment;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String string) {
        flag = 2;
        action = "showHistory";
        mEditText.setText(string);
        mEditText.setFocusableInTouchMode(false);
        mEditText.setFocusable(false);
        ivSearch.setVisibility(View.GONE);
        transaction = manager.beginTransaction();
        searchResultFragment = SearchResultFragment.newInstance(string, "");
        controlFragment(searchResultFragment);
        transaction.commit();
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                mEditText.setFocusableInTouchMode(true);
                mEditText.setFocusable(true);
                ivSearch.setVisibility(View.VISIBLE);
            }
        });
    }
}
