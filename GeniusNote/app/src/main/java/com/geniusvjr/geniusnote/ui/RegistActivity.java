package com.geniusvjr.geniusnote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geniusvjr.geniusnote.R;
import com.geniusvjr.geniusnote.model.User;
import com.geniusvjr.geniusnote.utils.Util;
import com.geniusvjr.tclibrary.activities.SlidingFinishActionBarActivity;
import com.geniusvjr.tclibrary.classes.InputFilter_Black;
import com.geniusvjr.tclibrary.classes.InputFilter_NumAndLetter;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dream on 16/1/30.
 */
public class RegistActivity extends SlidingFinishActionBarActivity implements View.OnClickListener {

    private Button btn_signUp;
    private EditText et_userName, et_password1, et_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    private void initView() {
        btn_signUp = (Button) findViewById(R.id.btn_regist);
        btn_signUp.setOnClickListener(this);

        //用户名及输入黑名单
        et_userName = (EditText) findViewById(R.id.signUp_userName);
        InputFilter_Black filter = new InputFilter_Black(new char[]{'<', '>', '/'});
        et_userName.setFilters(new InputFilter_Black[]{filter});

        // 密码
        et_password1 = (EditText) findViewById(R.id.signUp_password1);
        et_password2 = (EditText) findViewById(R.id.signUp_password2);
//        et_password1.setFilters(new InputFilter_NumAndLetter[]{new InputFilter_NumAndLetter()});

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_regist:
                signUp();
                break;
        }
    }

    private void signUp() {
        String userName = et_userName.getText().toString();
        String password1 = et_password1.getText().toString();
        String password2 = et_password2.getText().toString();

        if (password1 == null || password1.length() < 4) {
            Toast.makeText(getApplication(), "您输入的密码长度太短，请重新输入", Toast.LENGTH_LONG).show();
            return;
        }

        if (password1.length() > 16) {
            Toast.makeText(getApplication(), "您输入的密码长度大于16位，请重新输入。", Toast.LENGTH_LONG).show();
            return;

        }

        if (!Util.isNetworkConnected(this)) {
            toast("请检查您的网络");
        }
        if (password1.equals(password2)) {
            //开始提交注册信息
            User user = new User();
            user.setUsername(userName);
            user.setPassword(password1);
            user.signUp(this, new SaveListener() {
                @Override
                public void onSuccess() {
                    toast("注册成功");
                    Intent backLogin = new Intent(RegistActivity.this,
                            LoginActivity.class);
                    startActivity(backLogin);
                    RegistActivity.this.finish();
                }

                @Override
                public void onFailure(int i, String s) {
                    toast("用户名已被注册，请您换个名字");
                }
            });
        } else {
            toast("两次输入的密码不相同！");
        }
    }

    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    ;
}
