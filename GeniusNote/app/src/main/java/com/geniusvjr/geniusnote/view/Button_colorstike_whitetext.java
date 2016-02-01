package com.geniusvjr.geniusnote.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.geniusvjr.geniusnote.R;

public class Button_colorstike_whitetext extends Button{

	public Button_colorstike_whitetext(Context context, AttributeSet attrs) {
		super(context, attrs);
		if(isInEditMode()){
			return;
		}
		setBackgroundResource(R.drawable.btn_round_colorstrike);
		setTextColor(getResources().getColorStateList(R.drawable.font_primarycolor_white));
	}

	Button_colorstike_whitetext(Context context) {
		this(context, null);
	}
	
	
}
