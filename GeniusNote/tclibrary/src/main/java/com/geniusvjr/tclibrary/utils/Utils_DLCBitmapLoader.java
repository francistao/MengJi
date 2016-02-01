package com.geniusvjr.tclibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Double Level Cache 网络－本地两级缓存的图片加载
 * Created by dream on 16/2/1.
 */
public class Utils_DLCBitmapLoader {

    /**
     * 从本地文件中加载一张图片，有可能返回null,文件大小超过了制定的宽度或高度将被压缩
     * @param filePath	文件路径
     * @param maxWidth	最大宽度
     * @param maxHeight	最大高度
     */
    public Bitmap loadBitmapFromFille(String filePath, int maxWidth, int maxHeight)
    {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, option);
        if(option.outWidth > maxWidth || option.outHeight > maxHeight)
        {
            int wScale = option.outWidth / maxWidth;
            int hScale = option.outHeight / maxHeight;
            option.inSampleSize = wScale > hScale ? wScale : hScale;
            Log.i("陶程", "Utils_DLCBitmapLoader#loadBitmapFromFile(): " + "缩放倍数为：" + option.inSampleSize);
            if(option.inSampleSize < 1)
            {
                option.inSampleSize = 1;
            }
        }
        option.inJustDecodeBounds = false;
        Log.i("陶程",  "Utils_DLCBitmapLoader#loadBitmapFromFile(): " + "从本地加载图片的路径为：" + filePath);
        bitmap = BitmapFactory.decodeFile(filePath, option);
        return bitmap;
    }

    /**
     * @二级缓存 先从本地加载图片，如果没有，则从网络上加载
     * @本地保存 从网络加载的图片会保存到本地，本地文件名是网络地址的hashCode
     * @压缩图片 加载到内存中时，如果图片超过了指定的大小，压缩它
     * @param url	网络地址
     * @param cacheFolderPath	缓存的文件夹, eg: "sdcard/"
     * @param maxWidth	最大宽度
     * @param maxHeight	最大高度
     */
//    public Bitmap loadBitmap(String url, String cacheFolderPath, int maxWidth, int maxHeight)
//    {
//        String suffix = ".jpg";
//        if(url.contains(".png"))
//        {
//
//        }
//    }

}
