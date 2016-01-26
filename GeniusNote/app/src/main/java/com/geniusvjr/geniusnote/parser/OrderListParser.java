package com.geniusvjr.geniusnote.parser;

import com.geniusvjr.geniusnote.markdown.Markdown;

/**
 * Created by dream on 16/1/24.
 */
public class OrderListParser extends Markdown.MDParser {

    private static final String KEY = "^[0-9].*";
    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        if(!content.matches(KEY))
        {
            return Markdown.MDWord.NULL;
        }

        return new Markdown.MDWord("", 0, Markdown.MD_FMT_ORDER_LIST);
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        return Markdown.MDWord.NULL;
    }
}
