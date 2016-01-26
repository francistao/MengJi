package com.geniusvjr.geniusnote.parser;

import com.geniusvjr.geniusnote.markdown.Markdown;

/**
 * Created by dream on 16/1/24.
 */
public class BoldParser extends Markdown.MDParser {

    private static final String KEY = "**";
    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        return Markdown.MDWord.NULL;
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        if(!content.startsWith(KEY))
        {
            return Markdown.MDWord.NULL;
        }
        int position = content.indexOf(KEY, 2);
        if(position == -1)
        {
            return Markdown.MDWord.NULL;
        }
        return new Markdown.MDWord(content.substring(2, position), position+2, Markdown.MD_FMT_BOLD);
    }
}
