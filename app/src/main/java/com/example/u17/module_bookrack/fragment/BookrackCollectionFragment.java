package com.example.u17.module_bookrack.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.u17.R;
import com.example.u17.module_login.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @Author:king1033
 * @Desc:BookrackCollectionFragment
 * @Time:2016/9/12
 */
public class BookrackCollectionFragment extends Fragment implements View.OnClickListener{

    //登录
    @BindView(R.id.tv_please_login)
    TextView mCollectionLogin;


    private Context context;
    private boolean state;


    public static BookrackCollectionFragment newInstance() {
        BookrackCollectionFragment fragment = new BookrackCollectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state = LoginActivity.isLogin;
        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View view=inflater.inflate(R.layout.fragment_bookrack_collection, container, false);

        ButterKnife.bind(this,view);
        mCollectionLogin.setOnClickListener(this);
        if (state ==true){
            View view2=inflater.inflate(R.layout.fragment_bookrack_history, container, false);
            return view2;
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_please_login:
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                break;
            default:
                break;
        }

    }
}
