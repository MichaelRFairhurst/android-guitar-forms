package com.testception.fret_patterns;

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
import android.view.Menu;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.content.SharedPreferences;
import android.content.Intent;
import android.preference.PreferenceManager;
import java.util.List;
import java.util.ArrayList;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener
{
	private FretboardView fretboard;
	private FretPatternCollection patterns;
	private FretPattern nextp;
	private int speed = 100;
	private boolean running = false;
	private Handler handler = new Handler();

	private Runnable timer_showtext = new Runnable() {
		@Override
		public void run() {
			showNextPatternName();
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

		SharedPreferences prefs;
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);

		patterns = new FretPatternCollection(prefs);
		
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
			showNextPatternName();
		}
	}

	private void showNextPatternName() {
		nextp = patterns.getRandom(2);
		fretboard.clearFrettings();
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(nextp.getName());

		handler.postDelayed(timer_showfrets, 3000 + 100 * speed);
	}

	private void showFrets() {
		fretboard.setFrettings(nextp.getFrettings());

		handler.postDelayed(timer_showtext, 3000 + 100 * speed);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		startActivity(new Intent(this, PatternSettings.class));
		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.menu, menu);
		//return true;
		return false;
	}

	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		patterns = new FretPatternCollection(prefs);
	}

}
