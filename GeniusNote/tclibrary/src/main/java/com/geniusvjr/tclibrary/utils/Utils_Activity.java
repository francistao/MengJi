package com.geniusvjr.tclibrary.utils;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.geniusvjr.tclibrary.debug.Debug;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by dream on 16/1/31.
 */
public class Utils_Activity {
    private static Utils_Activity utils;

    private Utils_Activity()
    {

    }

    /**
     * 获得单例
     */
    public static Utils_Activity getInstance()
    {
        if(utils == null)
        {
            utils = new Utils_Activity();
        }
        return utils;
    }

    /**
     * 设置状态栏颜色的条件
     * 1、4.4以上
     * 2、主题是透明的
     */

    public void setStateBarColor(Activity activity, int color)
    {
        SystemBarTintManager manager = new SystemBarTintManager(activity);
        manager.setStatusBarTintEnabled(true);
        manager.setStatusBarTintColor(color);
    }

    /**
     * 设置Toolbar低于状态栏
     * toolbar的padding会设置为状态栏的高度。如果之前在其他地方设置了toolbar的padding会无效
     */
    public void setToolBarBellowStateBar(final Activity activity, final Toolbar toolbar)
    {
        if(Build.VERSION.SDK_INT < 19)
        {
            Debug.Log(this.getClass().getName(), "安卓版本号<19");
            return;
        }

        toolbar.post(new Runnable() {
            @Override
            public void run() {
                int stateBarHeight = Utils_PhoneInfo.getInstance().getStateBarHeight(activity);
                int left = toolbar.getPaddingLeft();
                int right = toolbar.getPaddingRight();
                int bottom = toolbar.getPaddingBottom();
                toolbar.setPadding(left, stateBarHeight, right, bottom);
            }
        });
    }


    /**
     * 设置View低于状态栏
     * 使用setToolBarBellowStateBar()方法代替更好
     */
    public void setBellowState(final Activity activity, final View view)
    {
        if(Build.VERSION.SDK_INT < 19)
        {
            Debug.Log(this.getClass().getName(), "安卓版本号<19");
            return;
        }
        view.post(new Runnable() {
            @Override
            public void run() {
                int stateBarHeight = Utils_PhoneInfo.getInstance().getStateBarHeight(activity);
                int left = view.getPaddingLeft();
                int right = view.getPaddingRight();
//				int top = toolbar.getPaddingTop();
                int bottom = view.getPaddingBottom();
                view.setPadding(left, /*top + */stateBarHeight, right, bottom);
            }
        });
    }

}
