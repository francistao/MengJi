package com.geniusvjr.tclibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by dream on 16/1/31.
 */
public class Utils_PhoneInfo {

    private static Utils_PhoneInfo utils;
    private Utils_PhoneInfo() {}
    public static Utils_PhoneInfo getInstance()
    {
        if(utils == null)
        {
            utils = new Utils_PhoneInfo();
        }
        return utils;
    }
    /**
     * 获得手机的IMEI码
     */
    public String getIMEI(Context context)
    {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = telephonyManager.getDeviceId();
        return IMEI;
    }

    /**
     * 获得手机状态栏的高度
     */
    public int getStateBarHeight(Activity activity)
    {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 获得手机安卓版本
     */
    public int getAndroidVersionCode()
    {
        return Build.VERSION.SDK_INT;
    }

}
