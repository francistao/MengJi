package com.geniusvjr.geniusnote.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.geniusvjr.geniusnote.base.BaseActivity;

/**
 * Created by dream on 16/1/22.
 */
public class WorldActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void initView() {
        TextView tv = new TextView(this);
        tv.setText("我的世界");
        setContentView(tv);
    }

    @Override
    public void initData() {

    }
}
