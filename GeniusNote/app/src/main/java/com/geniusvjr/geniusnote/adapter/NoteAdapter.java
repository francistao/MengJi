package com.geniusvjr.geniusnote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.geniusvjr.geniusnote.R;
import com.geniusvjr.geniusnote.adapter.viewholder.NoteViewHolder;
import com.geniusvjr.geniusnote.db.NoteDB;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by dream on 16/1/22.
 */
public class NoteAdapter extends BaseAdapter{

    private Context mContext;

    public NoteAdapter(Context context)
    {
        mContext = context;
    }

    @Override
    public int getCount() {
        return NoteDB.getInstance().size();
    }

    @Override
    public Object getItem(int position) {
        return NoteDB.getInstance().get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((NoteDB.Note)getItem(position)).key;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_note_item, null);
            NoteViewHolder holder = new NoteViewHolder();
            holder.mNoteDate = (TextView) convertView.findViewById(R.id.NoteDateText);
            holder.mNoteTitle = (TextView) convertView.findViewById(R.id.NoteTitleText);
            convertView.setTag(holder);
        }

        NoteDB.Note note = (NoteDB.Note) getItem(position);
        if(note != null)
        {
            NoteViewHolder holder = (NoteViewHolder) convertView.getTag();
            holder.mNoteDate.setText(getDateStr(note.date));
            holder.mNoteTitle.setText(note.title);
        }
        return convertView;
    }

    public static String getDateStr(long milliseconds) {
        return new SimpleDateFormat("yyyy年MM月dd日 EEEE HH点mm分", Locale.CHINA).format(milliseconds);
    }
}
