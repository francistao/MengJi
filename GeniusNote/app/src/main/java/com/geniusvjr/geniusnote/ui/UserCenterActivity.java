package com.geniusvjr.geniusnote.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusvjr.geniusnote.R;
import com.geniusvjr.geniusnote.model.User;
import com.geniusvjr.geniusnote.utils.Util;
import com.geniusvjr.tclibrary.activities.SlidingFinishActionBarActivity;
import com.geniusvjr.tclibrary.data.FinalValue;
import com.geniusvjr.tclibrary.data.Screen;
import com.geniusvjr.tclibrary.debug.Debug;
import com.geniusvjr.tclibrary.listeners.OnFinishListener;
import com.geniusvjr.tclibrary.utils.Utils_Activity;
import com.geniusvjr.tclibrary.utils.Utils_Http;
import com.geniusvjr.tclibrary.utils.Utils_SaveBitmap;
import com.geniusvjr.tclibrary.utils.Utils_TLCBitmapLoader;

import cn.bmob.v3.BmobUser;

/**
 * Created by dream on 16/1/30.
 */
public class UserCenterActivity extends SlidingFinishActionBarActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btn_changename, btn_changePassword, btn_feedback, btn_exitAccount, btn_updateProfile;
    private ImageView imageView;
    private TextView tv_profile;
    private SharedPreferences sp;
    SharedPreferences.Editor editor;

//    public static final int UPDATE_INFO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        sp = getSharedPreferences("UserInfo", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        editor = sp.edit();
        initViews();
    }

    private void initViews() {
        initToolbar();
        btn_changename = (Button) findViewById(R.id.usercenter_changename);
        btn_changePassword = (Button) findViewById(R.id.usercenter_changePassword);
        btn_exitAccount = (Button) findViewById(R.id.usercenter_exitAccount);
        btn_feedback = (Button) findViewById(R.id.usercenter_feedback);
        btn_updateProfile = (Button) findViewById(R.id.usercenter_updateProfile);
        imageView = (ImageView) findViewById(R.id.usercenter_imageView);
        tv_profile = (TextView) findViewById(R.id.usercenter_textview_profile);
        setImageViewProperties();

        imageView.setOnClickListener(this);
        btn_changePassword.setOnClickListener(this);
        btn_exitAccount.setOnClickListener(this);
        btn_changename.setOnClickListener(this);
        btn_feedback.setOnClickListener(this);
        btn_updateProfile.setOnClickListener(this);
    }

    /**
     * 设置头像的参数
     */
    private void setImageViewProperties() {
        Screen.initScreenMsg(this);
        imageView.getLayoutParams().height = (int) Screen.perHeight(30);

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.activity_usercenter_toolBar);
        String title = "设置";
        if (sp.getBoolean("login", false)) {
//            User user = (User) BmobUser.getCurrentUser(this);
//            title = user.getUsername() + "的用户中心";
            title = "用户中心";
            return;
        }
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.rgb(255, 255, 255));
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        Utils_Activity.getInstance().setToolBarBellowStateBar(this, toolbar);
        Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimaryDark));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.usercenter_exitAccount:
                BmobUser.logOut(this);   //清除缓存用户对象
                editor.putBoolean("login", false);
                editor.commit();
                Intent intent = new Intent(UserCenterActivity.this, NavigationViewActivity.class);
                startActivity(intent);
                break;
            case R.id.usercenter_updateProfile:

                if (!sp.getBoolean("login", false)) {
                    Toast.makeText(UserCenterActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, UpdateProfileActivity.class));
                }

                break;
            case R.id.usercenter_changename:
                if (!sp.getBoolean("login", false)) {
                    Toast.makeText(UserCenterActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, UpdateUsernameActivity.class));
                }
                break;
            case R.id.usercenter_changePassword:
                if (!sp.getBoolean("login", false)) {
                    Toast.makeText(UserCenterActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, UpdatePasswordActivity.class));
                }
                break;
            case R.id.usercenter_feedback:
                    startActivity(new Intent(this, FeedbackActivity.class));
                break;
        }
    }
}
