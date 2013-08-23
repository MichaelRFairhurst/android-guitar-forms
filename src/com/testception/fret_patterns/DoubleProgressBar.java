package com.testception.fret_patterns;

import android.widget.ProgressBar;
import android.content.Context;
import android.util.AttributeSet;

public class DoubleProgressBar extends ProgressBar {

	private int printablemax = 50;
	private int realmax = 100;
	private boolean flipped = false;

	public DoubleProgressBar(Context c, AttributeSet a) {
		super(c, a);
	}

	@Override
	public void setMax(int max) {
		realmax = max;
		printablemax = max >> 1;
		super.setMax(printablemax);
	}

	@Override
	public void setProgress(int progress) {
		if(progress > printablemax) {
			super.setProgress(realmax - progress);
		} else {
			super.setProgress(progress);
		}
	}
}
