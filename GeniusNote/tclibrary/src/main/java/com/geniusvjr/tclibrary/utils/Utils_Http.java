package com.geniusvjr.tclibrary.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.geniusvjr.tclibrary.debug.Debug;

/**
 * Created by dream on 16/1/31.
 */
public class Utils_Http {
    /**
     * 获得Uri的真实的绝对路径
     */
    public String getRealFilePath(final Context context, final Uri uri) {
        if (uri == null) {
            Debug.Log(this.getClass().getName() + "#getRealFilePath()", "Uri == null");
            return null;
        }

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        } else {
            Debug.Log(this.getClass().getName(), "scheme == ohter !");
        }

        Debug.Log(this.getClass().getName() + "#getRealFilePath()", data);

//        // 特意为魅族这脑残手机弄的
//        String keyString = "/document/primary:";
//        if (data == null && uri.getPath().contains(keyString)) {
//            String uriPath = uri.getPath();
//            int index = uriPath.indexOf(keyString) + keyString.length();
//            String back = uriPath.substring(index);
//
//            StringBuilder sb = new StringBuilder("/storage/emulated/0/");
//            sb.append(back);
//            return sb.toString();
//        }
//        Debug.Log(this.getClass().getName() + "#getRealFilePath()", data);
        return data;
    }

}

