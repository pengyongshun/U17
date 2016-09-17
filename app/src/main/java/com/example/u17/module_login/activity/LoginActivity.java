package com.example.u17.module_login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.u17.HomeActivity;
import com.example.u17.R;
import com.example.u17.base_uitls.EmptyUtils;
import com.example.u17.base_uitls.SPUtils;
import com.example.u17.module_login.constants.Constant;
import com.example.u17.module_login.constants.SaveInfo;
import com.example.u17.module_login.dao.AppDao;
import com.example.u17.module_login.dao.CallbackListener;
import com.example.u17.module_login.dao.LoginModel;
import com.example.u17.module_login.dao.UserDataManager;
import com.example.u17.module_login.dao.UserModel;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author:king1033
 * @Desc:
 * @Time:2016/9/9
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
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

    private UserDataManager mUserDataManager;
    public static boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginBack.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_image_view_login_back:
                //返回上一个界面
                finish();
                break;
            case R.id.id_mobile_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.id_login_confirm:
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


        if (isUserNameAndPwdValid()) {
            String userName = mUserName.getText().toString().trim();
            String userPwd = mUserPassword.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if(result==1){
                isLogin = true;
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);


                Toast.makeText(this, getString(R.string.login_sucess), Toast.LENGTH_SHORT).show();
            }else if(result==0){
                //login failed,user does't exist
                Toast.makeText(this, getString(R.string.login_fail),Toast.LENGTH_SHORT).show();
                // Reset errors.
                mUserName.setText("");
                mUserPassword.setText("");
            }
        }
    }
    public boolean isUserNameAndPwdValid() {
        if (mUserName.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            mUserName.setText("");
            mUserPassword.setText("");
            return false;
        } else if (mUserPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            mUserName.setText("");
            mUserPassword.setText("");
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
}
