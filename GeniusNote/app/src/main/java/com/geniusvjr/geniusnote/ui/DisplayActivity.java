package com.geniusvjr.geniusnote.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.geniusvjr.geniusnote.R;
import com.geniusvjr.geniusnote.base.BaseActivity;
import com.geniusvjr.geniusnote.markdown.MDReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by dream on 16/1/23.
 */
public class DisplayActivity extends BaseActivity {

    private static final String DEFAULT_DIR = Environment.getExternalStorageDirectory() + File.separator + "GeniusNote";

    private TextView mTextView;
    private MDReader mMDReader;
    private ScrollView mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(com.geniusvjr.geniusnote.R.drawable.actionbar_bg));
        super.onCreate(savedInstanceState);
        setContentView(com.geniusvjr.geniusnote.R.layout.activity_display);
        checkStorageDir();
        initData();

        initView();
    }

    @Override
    public void initView() {

        String content = getIntent().getExtras().getString("Content");
        mRootView = (ScrollView) findViewById(R.id.DisplayRootView);
        mTextView = (TextView) findViewById(R.id.DisplayTextView);
        mMDReader = new MDReader(content);
        mTextView.setTextKeepState(mMDReader.getFormattedContent(), TextView.BufferType.SPANNABLE);


    }

    @Override
    public void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save_md:
                saveAsMardown();
                break;
            case R.id.action_save_txt:
                saveAsRawContent();
                break;
            case R.id.action_save_img:
                saveAsBitmap();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public void checkStorageDir() {
        if (isSDCardMounted()) {
            File directory = new File(DEFAULT_DIR);
            if (!directory.exists()) {
                directory.mkdir();
            }
        }
    }

    public boolean checkSaveEnv() {
        if (!isSDCardMounted()) {
            Toast.makeText(this, "找不到SDCard!", Toast.LENGTH_LONG).show();
        }
        if (mMDReader.getContent().equals("")) {
            Toast.makeText(DisplayActivity.this, "没有内容，无法保存！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //保存为纯文本
    public void saveAsMardown() {
        if (!checkSaveEnv()) {
            return;
        }
        String filepath = DEFAULT_DIR + File.separator + mMDReader.getTitle() + ".md";
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath), "UTF-8"));
            writer.write(mMDReader.getContent());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "成功保存到:" + filepath, Toast.LENGTH_LONG).show();
    }

    public void saveAsRawContent() {
        if(!checkSaveEnv()) {
            return;
        }
        String filepath = DEFAULT_DIR+File.separator+mMDReader.getTitle()+".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath), "UTF-8"));
            writer.write(mMDReader.getRawContent());
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "成功保存到:"+filepath, Toast.LENGTH_LONG).show();
    }
    //保存为图片
    public void saveAsBitmap() {
        if (!checkSaveEnv()) {
            return;
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
        String filepath = DEFAULT_DIR + File.separator + mMDReader.getTitle() + ".jpg";
        try {
            FileOutputStream stream = new FileOutputStream(filepath);
            Bitmap bitmap = createBitmap(mRootView);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                Toast.makeText(this, "成功保存到:" + filepath, Toast.LENGTH_LONG).show();
            }
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap createBitmap(ScrollView v) {
        int width = 0, height = 0;
        for (int i = 0; i < v.getChildCount(); i++) {
            width += v.getChildAt(i).getWidth();
            height += v.getChildAt(i).getHeight();
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    public static Bitmap createBitmap(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return bitmap;
    }


}
