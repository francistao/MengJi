package com.geniusvjr.geniusnote.parser;

import com.geniusvjr.geniusnote.markdown.Markdown;

/**
 * Created by dream on 16/1/24.
 */
public class QuoteParser extends Markdown.MDParser {

    private static final char KEY = '>';

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        if(content.charAt(0)!= KEY)
        {
            return Markdown.MDWord.NULL;
        }
        return new Markdown.MDWord("", 1, Markdown.MD_FMT_QUOTE);
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        return Markdown.MDWord.NULL;
    }
}
