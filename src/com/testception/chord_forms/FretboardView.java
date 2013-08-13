package com.testception.chord_forms;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.util.AttributeSet;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;

public class FretboardView extends View {

	private ArrayList<Drawable> strings = new ArrayList<Drawable>();
	private ArrayList<Drawable> frets = new ArrayList<Drawable>();
	private List<Fretting> frettings = new ArrayList<Fretting>();
	private float strheight;
	private float stroffs;
	private int fretwidth = 7;
	private int fretoffs = 10;
	private float fretgap;
	private int lowestfret = 0;

	public FretboardView (Context context, AttributeSet attrs) {
		super(context, attrs);

		Resources res = getResources();

		for(int i = 0; i < 6; i++) {
			Drawable string = res.getDrawable(R.drawable.fretboard_string);
			strings.add(string);
		}

		for(int i = 0; i < 6; i++) {
			Drawable fret = res.getDrawable(R.drawable.fretboard_fret);
			fret.setColorFilter(0xFF444444 - 0x00020202 * i, Mode.SRC_OVER);
			frets.add(fret);
		}

		onSizeChanged(getWidth(), getHeight(), 0, 0);
	}

	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		h = getMeasuredHeight();
		Resources res = getResources();

		strheight = h / 6;
		stroffs = strheight / 2;
		for(int i = 0; i < 6; i++) {
			int myh = getStringY(i);
			strings.get(i).setBounds(0, myh - 1, w, myh + 1);
		}

		fretgap = (w - fretoffs * 2 - fretwidth) / 5;
		for(int i = 0; i < 6; i++) {
			int mygap = getFretX(i);
			frets.get(i).setBounds((int) Math.floor(mygap - fretwidth / 2), 3, (int) Math.floor(mygap + fretwidth / 2), h - 3);
		}
	}

	protected void onDraw(Canvas canvas) {
		for(Drawable f : frets) f.draw(canvas);
		for(Drawable s : strings) s.draw(canvas);

		Resources res = getResources();
		Paint paint = new Paint(); 
		paint.setStyle(Paint.Style.FILL);;
		paint.setColor(0xFF000000); 
		paint.setTextSize(10); 
		paint.setAntiAlias(true);

		for(Fretting f : frettings) {
			Drawable fretoval = res.getDrawable(R.drawable.fretboard_fretoval);
			int y = getStringY(f.getString());
			int x = getFretX(f.getFret() - lowestfret);
			fretoval.setBounds(x - 10, y - 10, x + 10, y + 10);
			fretoval.draw(canvas);
			String fretstring = String.valueOf(f.getFret());
			canvas.drawText(fretstring, x - paint.measureText(fretstring)/2, y + 4, paint);
		}
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width;
		int height;

		/*switch(heightMode) {
			case MeasureSpec.EXACTLY:
				width = widthSize;
				height = heightSize;
				break;

			case MeasureSpec.AT_MOST:*/
				width = widthSize;
				height = Math.min((int) Math.floor(width/2), 150);
				if(heightMode == MeasureSpec.AT_MOST && height > heightSize) height = heightSize;
			/*default:
				width = widthSize;
				height = (int) Math.floor(width/2);
		}*/

		setMeasuredDimension(width, height);
	}

	private int getFretX(int i) {
		return (int) Math.floor(fretgap * i + fretoffs);
	}

	private int getStringY(int i) {
		return (int) Math.floor(strheight * i + stroffs);
	}

	public void setFrettings(List<Fretting> newfrettings) {
		lowestfret = 24;
		for(Fretting f : newfrettings) {
			lowestfret = Math.min(lowestfret, f.getFret());
		}

		frettings = newfrettings;
		postInvalidate();
	}

	public void clearFrettings() {
		frettings.clear();
		postInvalidate();
	}

}
