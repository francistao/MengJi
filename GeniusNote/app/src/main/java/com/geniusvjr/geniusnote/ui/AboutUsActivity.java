package com.geniusvjr.geniusnote.ui;

import android.os.Bundle;
import android.view.Window;

import com.geniusvjr.geniusnote.R;
import com.geniusvjr.geniusnote.base.BaseActivity;
import com.geniusvjr.geniusnote.view.SildingFinishLayout;
import com.geniusvjr.tclibrary.activities.SlidingFinishActionBarActivity;

/**
 * 关于我们
 * Created by dream on 16/1/29.
 */
public class AboutUsActivity extends SlidingFinishActionBarActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aboutus);
//        SildingFinishLayout mSildingFinishLayout = (SildingFinishLayout) findViewById(R.id.sildingFinishLayout);
//        mSildingFinishLayout
//                .setOnSildingFinishListener(new SildingFinishLayout.OnSildingFinishListener() {
//
//                    @Override
//                    public void onSildingFinish() {
//                        AboutUsActivity.this.finish();
//                    }
//                });
//
//        mSildingFinishLayout.setTouchView(mSildingFinishLayout);
    }




//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(0, R.anim.base_slide_right_out);
//    }

}
