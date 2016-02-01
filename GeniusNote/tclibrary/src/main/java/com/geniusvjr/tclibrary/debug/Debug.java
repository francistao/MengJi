package com.geniusvjr.tclibrary.debug;

import android.util.Log;

/**
 * Created by dream on 16/1/30.
 */
public class Debug {

    final static String TAG = "陶程";
    /**
     * 在logcat显示一条消息，TAG默认为陶程
     */
    public static void Log(String msg)
    {
        Log.i(TAG, msg);
    }

    /**
     * 在logcat显示一条信息,TAG默认为陶程
     */
    public static void Log(String where, Object obj)
    {
        Log.i(TAG, "☛ at " + where +  ": \n" + obj);
    }

    /**
     * 在logcat显示一个列表的信息，TAG默认为郑海鹏
     */
    public static void Log(String where, String[] titles, Object[] objs){
        Log.i(TAG, "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        Log.i(TAG, where);
        Log.i(TAG, "----------------------------------------");

        for(int i = 0; i < objs.length; i++){
            try {
                Log.i(TAG, titles[i] + "\t:" + objs[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                Log.i(TAG, "无标题\t:" + objs[i]);
            }

        }
        Log.i(TAG, "↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");
    }


}
