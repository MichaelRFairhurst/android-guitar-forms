package com.testception.fret_patterns;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Button;
import android.view.Menu;
import android.content.SharedPreferences;
import android.content.Intent;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {
	private ProgressBar mprogress;
	private int progress = 0;
	private FretboardView fretboard;
	private FretPatternCollection patterns;
	private FretPattern nextp;
	private int speed = 60;
	private boolean running = false;
	private Handler handler = new Handler();

	private Runnable tick = new Runnable() {
		@Override
		public void run() {
			tick();
		}
	};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		SharedPreferences prefs;
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		patterns = new FretPatternCollection(prefs);

		fretboard = (FretboardView) findViewById(R.id.fretboard);
		mprogress = (ProgressBar) findViewById(R.id.progress);
		mprogress.setMax(5000);
		((SeekBar) findViewById(R.id.speed)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				speed = 10 + (progress>>1);
			}
			public void onStartTrackingTouch(SeekBar seekBar) {}
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});
    }

	@Override
	public void onStop() {
		if(running) startStop(findViewById(R.id.startstop));
		super.onStop();
	}

	public void startStop(View view) {
		running = !running;
		((Button) view).setText(running ? R.string.stop : R.string.start);
		if(!running) {
			handler.removeCallbacksAndMessages(null);
		} else {
			tick();
		}
	}

	private void tick() {
		int lprogress = progress;
		progress = (progress + speed) % 5000;
		if(nextp == null || progress < lprogress) showNextPatternName();
		if(lprogress <= 2500 && progress >= 2500) showFrets();
		mprogress.setProgress(progress);
		handler.postDelayed(tick, 17);
	}

	private void showNextPatternName() {
		nextp = patterns.getRandom(2);
		fretboard.clearFrettings();
		TextView name = (TextView) findViewById(R.id.name);
		name.setText(nextp.getName());
	}

	private void showFrets() {
		fretboard.setFretPattern(nextp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		startActivity(new Intent(this, PatternSettingsActivity.class));
		return false;
	}

}
