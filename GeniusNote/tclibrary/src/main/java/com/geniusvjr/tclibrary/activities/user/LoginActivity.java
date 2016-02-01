package com.geniusvjr.tclibrary.activities.user;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.geniusvjr.tclibrary.R;
import com.geniusvjr.tclibrary.data.Screen;

/**
 * 用户登录类
 * Created by dream on 16/1/30.
 */
public class LoginActivity extends AppCompatActivity{
    /**
     * 最底层布局
     */
    public RelativeLayout bgLayout;

    /**
     * 文本编辑框,用户名
     */
    public EditText editText_UserName;

    /**
     *文本编辑框，密码
     */
    public EditText editText_Password;

    /**
     * 按钮，登录
     * @param savedInstanceState
     * @param persistentState
     */
    public Button button_login;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_log_in);
        setTitle(R.string.title_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bgLayout = (RelativeLayout) findViewById(R.id.library_logIn_bgLayout);
        editText_UserName = (EditText) findViewById(R.id.library_logIn_userName);
        editText_Password = (EditText) findViewById(R.id.library_logIn_password);
//		button_logIn = (Button) findViewById(R.id.library_logIn_logIn);
        editText_UserName.getLayoutParams().width = (int) Screen.perWidth(50);
        editText_Password.getLayoutParams().width = (int) Screen.perWidth(50);
    }
}
