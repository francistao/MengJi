package com.geniusvjr.tclibrary.classes;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by dream on 16/1/30.
 */
public class InputFilter_NumAndLetter implements InputFilter{

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if(!source.toString().matches("[a-zA-Z0-9_-]]"))
        {
            return "";
        }
        return null;
    }
}
