package com.geniusvjr.geniusnote.parser;

import com.geniusvjr.geniusnote.markdown.Markdown;

/**
 * Created by dream on 16/1/24.
 */
public class CenterParser extends Markdown.MDParser {
    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        return Markdown.MDWord.NULL;
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        if(content.charAt(0) == '{' && content.charAt(content.length()-1)=='}')
        {
            int length = content.length();
            return new Markdown.MDWord(content.substring(1, length-1), length, Markdown.MD_FMT_CENTER);
        }
        return Markdown.MDWord.NULL;
    }
}
