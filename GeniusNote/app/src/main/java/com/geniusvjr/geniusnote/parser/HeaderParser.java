package com.geniusvjr.geniusnote.parser;

import com.geniusvjr.geniusnote.markdown.Markdown;

/**
 * Created by dream on 16/1/24.
 */
public class HeaderParser extends Markdown.MDParser{

    public static final String HEADER = "# ";
    public static final String HEADER2 = "## ";
    public static final String HEADER3 = "### ";

    @Override
    public Markdown.MDWord parseLineFmt(String content) {
        if(content.startsWith(HEADER))
        {
            return new Markdown.MDWord("", HEADER.length(), Markdown.MD_FMT_HEADER1);
        }
        else if(content.startsWith(HEADER2))
        {
            return new Markdown.MDWord("", HEADER2.length(), Markdown.MD_FMT_HEADER2);
        }
        else if(content.startsWith(HEADER3))
        {
            return new Markdown.MDWord("", HEADER3.length(), Markdown.MD_FMT_HEADER3);
        }
        return Markdown.MDWord.NULL;
    }

    @Override
    public Markdown.MDWord parseWordFmt(String content) {
        return Markdown.MDWord.NULL;
    }
}
