package com.testception.chord_forms;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Button;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends Activity
{
	private ArrayList<EditText> frets = new ArrayList<EditText>();
	private ChordCollection chords = new ChordCollection();
	private ChordPosition nextc;
	private int speed;
	private boolean running = false;
	private Handler handler = new Handler();

	private Runnable timer_showtext = new Runnable() {
		@Override
		public void run() {
			showNextChordName();
		}
	};

	private Runnable timer_showfrets = new Runnable() {
		@Override
		public void run() {
			showFrets();
		}
	};

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		generateFrets();
		generateStringLabels();

		((SeekBar) findViewById(R.id.speed)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				speed = 100 - progress;
			}
			public void onStartTrackingTouch(SeekBar seekBar) {}
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});
    }

	public void startStop(View view) {
		running = !running;
		((Button) view).setText(running ? "Stop" : "Start");
		((TextView) findViewById(R.id.name)).setText("Press Start To Begin");
		if(!running) {
			clearFrets();
			handler.removeCallbacksAndMessages(null);
		} else {
			showNextChordName();
		}
	}

	private void showNextChordName() {
		clearFrets();
		nextc = chords.getRandom(Chord.MAJOR | Chord.MINOR);
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(nextc.getName());

		handler.postDelayed(timer_showfrets, 3000 + 100 * speed);
	}

	private void clearFrets() {
		int i;
		for(i = 0; i < 12; i++) frets.get(i).setText("");
	}

	private void showFrets() {
		int i;
		for(i = 0; i < 6; i++) {
			List<Fretting> fs = nextc.getFrettingsForString(i);
			EditText first = frets.get(i<<1);
			EditText second = frets.get((i<<1) + 1);
			first.setText(String.valueOf(fs.get(0).getFret()));
			if(fs.size() > 1) second.setText(String.valueOf(fs.get(1).getFret()));
		}

		handler.postDelayed(timer_showtext, 3000 + 100 * speed);
	}

	private void generateFrets() {
		int i = 0;

		RelativeLayout fretsholder = (RelativeLayout) findViewById(R.id.fretsholder);

		for(i = 0; i < 12; i++) {
			RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(45, 45);
			lp.leftMargin = 20 + (i % 2) * 40;
			lp.topMargin = (i >> 1) * 50;

			EditText fret = new EditText(this);
			fret.setRawInputType(0);
			fret.setFocusable(false);
			fret.setLayoutParams(lp);
			fret.setGravity(Gravity.CENTER);
			fret.setWidth((int) Math.floor(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics())));
			fretsholder.addView(fret);
			frets.add(fret);
		}

		Fretting a = new Fretting(3,1);
	}

	private void generateStringLabels() {
		int i;
		char[] stringnames = { 'E', 'B', 'G', 'D', 'A', 'E' };
		RelativeLayout fretsholder = (RelativeLayout) findViewById(R.id.fretsholder);

		for(i = 0; i < 6; i++) {
			RelativeLayout.LayoutParams labelp = new RelativeLayout.LayoutParams(30,30);
			labelp.topMargin = i * 50 + 10;
			TextView label = new TextView(this);
			label.setLayoutParams(labelp);
			label.setText(Character.toString(stringnames[i]) + " -");
			fretsholder.addView(label);
		}
	}
}
