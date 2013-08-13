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
	private FretboardView fretboard;
	private ChordCollection chords = new ChordCollection();
	private ChordPosition nextc;
	private int speed = 100;
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

		fretboard = (FretboardView) findViewById(R.id.fretboard);
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
		if(!running) {
			handler.removeCallbacksAndMessages(null);
		} else {
			showNextChordName();
		}
	}

	private void showNextChordName() {
		nextc = chords.getRandom(Chord.MAJOR | Chord.MINOR);
		fretboard.clearFrettings();
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(nextc.getName());

		handler.postDelayed(timer_showfrets, 3000 + 100 * speed);
	}

	private void showFrets() {
		fretboard.setFrettings(nextc.getFrettings());

		handler.postDelayed(timer_showtext, 3000 + 100 * speed);
	}
}
