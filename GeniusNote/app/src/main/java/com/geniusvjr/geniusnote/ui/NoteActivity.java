package com.geniusvjr.geniusnote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.geniusvjr.geniusnote.R;
import com.geniusvjr.geniusnote.base.BaseActivity;
import com.geniusvjr.geniusnote.db.NoteDB;
import com.geniusvjr.geniusnote.markdown.MDWriter;

import java.util.Calendar;

/**
 * Created by dream on 16/1/22.
 */
public class NoteActivity extends BaseActivity {

    private NoteDB.Note mNote = new NoteDB.Note();
    private MDWriter mMDWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initView();
        mMDWriter = new MDWriter((EditText) findViewById(R.id.NoteEditText));
        mNote.key = getIntent().getLongExtra("NoteId", -1);
        if(mNote.key != -1)
        {
            NoteDB.Note note = NoteDB.getInstance().get(mNote.key);
            if(note != null)
            {
                mMDWriter.setContent(note.content);
                mNote = note;
            }
            else
            {
                mNote.key = -1;
            }
        }
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_display) {
            onSaveNote();
            Intent intent = new Intent(this,DisplayActivity.class);
            intent.putExtra("Content",mMDWriter.getContent());
            startActivity(intent);
            return true;
        }
        else if(id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickHeader(View v) {
        mMDWriter.setAsHeader();
    }

    public void onClickCenter(View v) {
        mMDWriter.setAsCenter();
    }

    public void onClickList(View v) {
        mMDWriter.setAsList();
    }

    public void onClickBold(View v) {
        mMDWriter.setAsBold();
    }

    public void onClickQuote(View v) {
        mMDWriter.setAsQuote();
    }

    public void onSaveNote() {
        mNote.title = mMDWriter.getTitle();
        mNote.content = mMDWriter.getContent();
        if(mNote.key==-1) {
            if(!"".equals(mNote.content)) {
                mNote.date = Calendar.getInstance().getTimeInMillis();
                NoteDB.getInstance().insert(mNote);
            }
        }
        else {
            NoteDB.getInstance().update(mNote);
        }
    }

}
