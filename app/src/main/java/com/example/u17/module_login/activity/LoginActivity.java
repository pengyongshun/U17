package com.example.u17.module_login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u17.R;
import com.example.u17.base_uitls.EmptyUtils;
import com.example.u17.module_login.constants.Constant;
import com.example.u17.module_login.constants.SaveInfo;
import com.example.u17.module_login.dao.AppDao;
import com.example.u17.module_login.dao.CallbackListener;
import com.example.u17.module_login.dao.LoginModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/9
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    //返回图标
    @BindView(R.id.icon_image_view_login_back)
    ImageView mLoginBack;
    //邮箱快速注册
    @BindView(R.id.id_mobile_register)
    TextView mRegister;
    //用户名输入框
    @BindView(R.id.login_edit_text_account_number)
    EditText mUserName;
    //密码输入框
    @BindView(R.id.login_edit_text_password)
    EditText mUserPassword;
    //登录按钮
    @BindView(R.id.id_login_confirm)
    TextView mLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_image_view_login_back:
                //返回上一个界面
                finish();
                break;
            case R.id.id_mobile_register:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_edit_text_account_number:
                login();
                break;




            default:
                break;

        }
    }
    private void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void login() {
        final String email = mUserName.getText().toString().trim();
        final String pass = mUserPassword.getText().toString().trim();
        if(EmptyUtils.emptyOfString(email)||EmptyUtils.emptyOfString(pass))
        {
            toastMsg("邮箱或者密码不能为空");
            return;
        }
        AppDao.getInstance().userLogin(email,pass,new CallbackListener<LoginModel>()
        {

            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                Log.i("cjj","res----->"+result);
            }

            @Override
            public void onSuccess(LoginModel result) {
                super.onSuccess(result);
                int status = Integer.valueOf(result.ErrCode);
                if(Constant.success == status)
                {
//                    toastMsg("登录成功");

//                    UserInfo = new UserModel();
//                    UserInfo.email = result.Return.Email;
//                    UserInfo.pass = pass;

                   // SPUtils.saveObject(SaveInfo.KEY_LOGIN, UserInfo);
                    //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    LoginActivity.this.finish();
                }else
                {
                    toastMsg(result.ErrMsg);
                }

              //  closeProDialog();
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                toastMsg(e.toString());
                toastMsg(Constant.NET_ERROR);
               // closeProDialog();
            }
        });
    }

}
