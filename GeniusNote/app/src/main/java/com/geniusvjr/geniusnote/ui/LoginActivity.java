package com.geniusvjr.geniusnote.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geniusvjr.geniusnote.R;
import com.geniusvjr.geniusnote.base.BaseActivity;
import com.geniusvjr.geniusnote.model.User;
import com.geniusvjr.geniusnote.utils.Util;
import com.geniusvjr.tclibrary.activities.SlidingFinishActionBarActivity;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dream on 16/1/26.
 */
public class LoginActivity extends SlidingFinishActionBarActivity implements View.OnClickListener {

    private Button btn_login;
    private Button btn_regist;
    private EditText et_password;
    private EditText et_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    public void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_regist = (Button) findViewById(R.id.btn_regist);
        btn_login.setOnClickListener(this);
        btn_regist.setOnClickListener(this);

        et_username = (EditText) findViewById(R.id.login_username);
        et_password = (EditText) findViewById(R.id.login_password);

//        SharedPreferences sp = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
//        String userName = sp.getString("userName", "");
//        String password = sp.getString("password", "");
//        et_username.setText(userName);
//        et_password.setText(password);


    }

    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_login:
                String userName = et_username.getText().toString();
                String password = et_password.getText().toString();
                btn_login.setClickable(false);
                logIn(userName, password);
                break;
            case R.id.btn_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
        }
    }

    private void logIn(final String userName, final String password) {
        if(!Util.isNetworkConnected(this))
        {
            toast("请检查网络状态");
        }
        else if(userName.equals("")||password.equals(""))
        {
            toast("请正确输入账号密码");
        }
        else
        {
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password);
            user.login(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    toast("登录成功");
                    //保存用户信息
                    saveUserInfo(userName, password);
                    //跳转到主页面
                    Intent toHome = new Intent(LoginActivity.this, NavigationViewActivity.class);
                    startActivity(toHome);
                    finish();
                }

                @Override
                public void onFailure(int i, String s) {

                }
            });
        }
    }

    //保存用户的登录纪录
    private void saveUserInfo(String username, String password)
    {
        SharedPreferences sp = getSharedPreferences("UserInfo", Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("login", true);
        editor.commit();
    }

    public void toast(String toast)
    {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
