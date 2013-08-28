package com.testception.fret_patterns;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.lang.ClassCastException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

class FretPatternCollection implements OnSharedPreferenceChangeListener {

	private List<FretPattern> patterns;
	private SharedPreferences prefs;
	private Random rand = new Random();
	private int lastindex = 0;

	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		createFromPreferences(prefs);
	}

	public FretPatternCollection(SharedPreferences myprefs) {
		createFromPreferences(myprefs);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	public FretPattern getRandom(int type) {
		if(patterns.size() == 0) return new FretPattern("None selected", new ArrayList<Fretting>(), 0, new Note("E"));

		int next = rand.nextInt(patterns.size());
		if(next == lastindex) next = (next + 1) % patterns.size();
		lastindex = next;

		return patterns.get(next);
	}

	private void createFromPreferences(SharedPreferences myprefs) {
		prefs = myprefs;
		patterns = new ArrayList<FretPattern>();

		addMajorBasedScales();
		addThreeNoteMajorBasedScales();
		addPentatonics();
		addSweeps();

		inPositionRangeOnly();
		inSelectedKeysOnly();
	}

	private void inPositionRangeOnly() {
		ArrayList<FretPattern> difference = new ArrayList<FretPattern>();

		for(FretPattern f : patterns)
		if(f.getPosition() < Integer.valueOf(prefs.getString("pref_minimum_position", "0"))
			|| f.getPosition() > Integer.valueOf(prefs.getString("pref_maximum_position", "8")))
			difference.add(f);

		patterns.removeAll(difference);
	}

	private void inSelectedKeysOnly() {
		if(prefs.getBoolean("pref_rand_root_notes", true)) return;
		ArrayList<FretPattern> difference = new ArrayList<FretPattern>();

		for(FretPattern f : patterns)
		if(f.getRoot().getName() != "E")
			difference.add(f);

		patterns.removeAll(difference);
	}

	private void addModalBasesOfMajor(List<FretPattern> mscales, String prefsuffix, String namesuffix) {
		String[] modes = { "dorian", "phrygian", "lydian", "mixolydian", "minor", "locrian" };
		int[] rootchanges = { 2, 4, 5, 7, 9, 11 };

		for(FretPattern major : mscales) {
			if(prefs.getBoolean("pref_major" + prefsuffix, true)) patterns.add(major);
			for(int i = 0; i < modes.length; i++) {
				if(prefs.getBoolean("pref_" + modes[i] + prefsuffix, false)) {
					FretPattern mode = major.transpose(0);
					if(prefs.getString("pref_position_type", "degree").equals("degree")) {
						int newposition = major.getPosition() + 6 - i;
						mode.setPosition(newposition > 7 ? newposition - 7 : newposition);
					}
					mode.setName(modes[i] + namesuffix);
					mode.setRoot(mode.getRoot().transpose(rootchanges[i]));
					patterns.add(mode);
				}
			}
		}
	}

	/**
	 * Must get all keys, convert to all position-normalized modes, and then filter
	 * out wrong bases later at the end
	 */
	private List<List<FretPattern>> getAllKeys(List<FretPattern> base) {
		List<List<FretPattern>> keys = new ArrayList<List<FretPattern>>();

		for(int i = 1; i < 12; i++) {
			List<FretPattern> thiskey = new ArrayList<FretPattern>();

			for(FretPattern f : base)
				thiskey.add(f.transpose(i));

			keys.add(thiskey);
		}
		return keys;
	}

	private List<FretPattern> normalizePositions(List<FretPattern> set) {
		if(prefs.getString("pref_position_type", "degree").equals("degree")) return set;

		int lowestplayablepos = 100;
		int lowestfret = 100;
		int highestpos = 0;

		for(FretPattern f : set) {
			if(lowestfret > f.getLowestFret()) {
				lowestfret = f.getLowestFret();
				lowestplayablepos = f.getPosition();
			}
			if(highestpos > f.getPosition())
				highestpos = f.getPosition();
		}

		for(FretPattern f : set)
			f.setPosition(1 + f.getPosition() - lowestplayablepos + (f.getPosition() < lowestplayablepos ? highestpos : 0));

		return set;
	}

	private void addSweeps() {
		if(prefs.getBoolean("pref_eight_note_minor_sweeps", false)) {
			ArrayList<FretPattern> forms = new ArrayList<FretPattern>();
			ArrayList<Fretting> chord = new ArrayList<Fretting>();
			chord.add(new Fretting(3, 0));
			chord.add(new Fretting(0, 0));
			chord.add(new Fretting(0, 1));
			chord.add(new Fretting(0, 2));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(3, 5));
			chord.add(new Fretting(0, 5));
			forms.add(new FretPattern("minor 8 note sweep", chord, 1, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(3, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(5, 3));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(3, 5));
			forms.add(new FretPattern("minor 8 note sweep", chord, 2, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(3, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(5, 3));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			chord.add(new Fretting(3, 5));
			forms.add(new FretPattern("minor 8 note sweep", chord, 3, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(8, 1));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(10, 4));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			forms.add(new FretPattern("minor 8 note sweep", chord, 4, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(12, 2));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(10, 4));
			chord.add(new Fretting(12, 5));
			chord.add(new Fretting(7, 5));
			forms.add(new FretPattern("minor 8 note sweep", chord, 5, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(15, 0));
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(12, 2));
			chord.add(new Fretting(14, 3));
			chord.add(new Fretting(14, 4));
			chord.add(new Fretting(10, 4));
			chord.add(new Fretting(12, 5));
			forms.add(new FretPattern("minor 8 note sweep", chord, 6, new Note("E")));

			for(List<FretPattern> key : getAllKeys(forms))
			for(FretPattern position : normalizePositions(key))
				patterns.add(position);
		}

		if(prefs.getBoolean("pref_eight_note_major_sweeps", false)) {
			ArrayList<FretPattern> forms = new ArrayList<FretPattern>();
			ArrayList<Fretting> chord = new ArrayList<Fretting>();
			chord.add(new Fretting(4, 0));
			chord.add(new Fretting(0, 0));
			chord.add(new Fretting(0, 1));
			chord.add(new Fretting(1, 2));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(4, 5));
			chord.add(new Fretting(0, 5));
			forms.add(new FretPattern("major 8 note sweep", chord, 1, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(4, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(6, 3));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(4, 5));
			forms.add(new FretPattern("major 8 note sweep", chord, 2, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(4, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(6, 3));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			chord.add(new Fretting(4, 5));
			forms.add(new FretPattern("major 8 note sweep", chord, 3, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(9, 1));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(11, 4));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			forms.add(new FretPattern("major 8 note sweep", chord, 4, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(9, 1));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(11, 4));
			chord.add(new Fretting(12, 5));
			chord.add(new Fretting(7, 5));
			forms.add(new FretPattern("major 8 note sweep", chord, 5, new Note("E")));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(16, 0));
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(13, 2));
			chord.add(new Fretting(14, 3));
			chord.add(new Fretting(14, 4));
			chord.add(new Fretting(11, 4));
			chord.add(new Fretting(12, 5));
			forms.add(new FretPattern("major 8 note sweep", chord, 6, new Note("E")));

			for(List<FretPattern> key : getAllKeys(forms))
			for(FretPattern position : normalizePositions(key))
				patterns.add(position);
		}
	}

	private void addMajorBasedScales() {
		ArrayList<FretPattern> mscales = new ArrayList<FretPattern>();
		ArrayList<Fretting> scale = new ArrayList<Fretting>();
		scale.add(new Fretting(3, 0));
		scale.add(new Fretting(1, 0));
		scale.add(new Fretting(0, 0));
		scale.add(new Fretting(3, 1));
		scale.add(new Fretting(1, 1));
		scale.add(new Fretting(3, 2));
		scale.add(new Fretting(2, 2));
		scale.add(new Fretting(0, 2));
		scale.add(new Fretting(3, 3));
		scale.add(new Fretting(2, 3));
		scale.add(new Fretting(0, 3));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(1, 4));
		scale.add(new Fretting(0, 4));
		scale.add(new Fretting(3, 5));
		scale.add(new Fretting(1, 5));
		mscales.add(new FretPattern("major scale", scale, 1, new Note("F")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(6, 0));
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(3, 0));
		scale.add(new Fretting(6, 1));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(3, 1));
		scale.add(new Fretting(5, 2));
		scale.add(new Fretting(3, 2));
		scale.add(new Fretting(2, 2));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(3, 3));
		scale.add(new Fretting(2, 3));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(6, 5));
		scale.add(new Fretting(5, 5));
		scale.add(new Fretting(3, 5));
		mscales.add(new FretPattern("major scale", scale, 2, new Note("F")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(8, 0));
		scale.add(new Fretting(6, 0));
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(6, 1));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(5, 2));
		scale.add(new Fretting(8, 3));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(8, 4));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(8, 5));
		scale.add(new Fretting(6, 5));
		scale.add(new Fretting(5, 5));
		mscales.add(new FretPattern("major scale", scale, 3, new Note("F")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(8, 0));
		scale.add(new Fretting(6, 0));
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(6, 1));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(5, 2));
		scale.add(new Fretting(8, 3));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(8, 4));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(8, 5));
		scale.add(new Fretting(6, 5));
		mscales.add(new FretPattern("major scale", scale, 4, new Note("F")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(10, 0));
		scale.add(new Fretting(8, 0));
		scale.add(new Fretting(11, 1));
		scale.add(new Fretting(10, 1));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(10, 2));
		scale.add(new Fretting(9, 2));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(10, 3));
		scale.add(new Fretting(8, 3));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(10, 4));
		scale.add(new Fretting(8, 4));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(10, 5));
		scale.add(new Fretting(8, 5));
		mscales.add(new FretPattern("major scale", scale, 5, new Note("F")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(13, 0));
		scale.add(new Fretting(12, 0));
		scale.add(new Fretting(10, 0));
		scale.add(new Fretting(13, 1));
		scale.add(new Fretting(11, 1));
		scale.add(new Fretting(10, 1));
		scale.add(new Fretting(12, 2));
		scale.add(new Fretting(10, 2));
		scale.add(new Fretting(9, 2));
		scale.add(new Fretting(12, 3));
		scale.add(new Fretting(10, 3));
		scale.add(new Fretting(13, 4));
		scale.add(new Fretting(12, 4));
		scale.add(new Fretting(10, 4));
		scale.add(new Fretting(13, 5));
		scale.add(new Fretting(12, 5));
		scale.add(new Fretting(10, 5));
		mscales.add(new FretPattern("major scale", scale, 6, new Note("F")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(3, 0));
		scale.add(new Fretting(1, 0));
		scale.add(new Fretting(0, 0));
		scale.add(new Fretting(3, 1));
		scale.add(new Fretting(1, 1));
		scale.add(new Fretting(3, 2));
		scale.add(new Fretting(2, 2));
		scale.add(new Fretting(0, 2));
		scale.add(new Fretting(3, 3));
		scale.add(new Fretting(2, 3));
		scale.add(new Fretting(0, 3));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(1, 4));
		scale.add(new Fretting(0, 4));
		scale.add(new Fretting(3, 5));
		scale.add(new Fretting(1, 5));
		scale.add(new Fretting(0, 5));
		mscales.add(new FretPattern("major scale", scale, 7, new Note("F")));

		for(List<FretPattern> key : getAllKeys(mscales))
			addModalBasesOfMajor(normalizePositions(key), "_scales", " scale");
	}

	private void addThreeNoteMajorBasedScales() {
		ArrayList<FretPattern> m3scale = new ArrayList<FretPattern>();
		ArrayList<Fretting> scale = new ArrayList<Fretting>();
		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(8, 0));
		scale.add(new Fretting(7, 0));
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(7, 1));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(5, 2));
		scale.add(new Fretting(4, 2));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(4, 3));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(7, 5));
		scale.add(new Fretting(5, 5));
		scale.add(new Fretting(3, 5));
		m3scale.add(new FretPattern("major 3 note scale", scale, 1, new Note("G")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(10, 0));
		scale.add(new Fretting(8, 0));
		scale.add(new Fretting(7, 0));
		scale.add(new Fretting(10, 1));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(7, 1));
		scale.add(new Fretting(9, 2));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(5, 2));
		scale.add(new Fretting(9, 3));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(9, 4));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(8, 5));
		scale.add(new Fretting(7, 5));
		scale.add(new Fretting(5, 5));
		m3scale.add(new FretPattern("major 3 note scale", scale, 2, new Note("G")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(12, 0));
		scale.add(new Fretting(10, 0));
		scale.add(new Fretting(8, 0));
		scale.add(new Fretting(12, 1));
		scale.add(new Fretting(10, 1));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(11, 2));
		scale.add(new Fretting(9, 2));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(10, 3));
		scale.add(new Fretting(9, 3));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(10, 4));
		scale.add(new Fretting(9, 4));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(10, 5));
		scale.add(new Fretting(8, 5));
		scale.add(new Fretting(7, 5));
		m3scale.add(new FretPattern("major 3 note scale", scale, 3, new Note("G")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(14, 0));
		scale.add(new Fretting(12, 0));
		scale.add(new Fretting(10, 0));
		scale.add(new Fretting(13, 1));
		scale.add(new Fretting(12, 1));
		scale.add(new Fretting(10, 1));
		scale.add(new Fretting(12, 2));
		scale.add(new Fretting(11, 2));
		scale.add(new Fretting(9, 2));
		scale.add(new Fretting(12, 3));
		scale.add(new Fretting(10, 3));
		scale.add(new Fretting(9, 3));
		scale.add(new Fretting(12, 4));
		scale.add(new Fretting(10, 4));
		scale.add(new Fretting(9, 4));
		scale.add(new Fretting(12, 5));
		scale.add(new Fretting(10, 5));
		scale.add(new Fretting(8, 5));
		m3scale.add(new FretPattern("major 3 note scale", scale, 4, new Note("G")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(15, 0));
		scale.add(new Fretting(14, 0));
		scale.add(new Fretting(12, 0));
		scale.add(new Fretting(15, 1));
		scale.add(new Fretting(13, 1));
		scale.add(new Fretting(12, 1));
		scale.add(new Fretting(14, 2));
		scale.add(new Fretting(12, 2));
		scale.add(new Fretting(11, 2));
		scale.add(new Fretting(14, 3));
		scale.add(new Fretting(12, 3));
		scale.add(new Fretting(10, 3));
		scale.add(new Fretting(14, 4));
		scale.add(new Fretting(12, 4));
		scale.add(new Fretting(10, 4));
		scale.add(new Fretting(14, 5));
		scale.add(new Fretting(12, 5));
		scale.add(new Fretting(10, 5));
		m3scale.add(new FretPattern("major 3 note scale", scale, 5, new Note("G")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(3, 0));
		scale.add(new Fretting(2, 0));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(3, 1));
		scale.add(new Fretting(1, 1));
		scale.add(new Fretting(4, 2));
		scale.add(new Fretting(2, 2));
		scale.add(new Fretting(0, 2));
		scale.add(new Fretting(4, 3));
		scale.add(new Fretting(2, 3));
		scale.add(new Fretting(0, 3));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(2, 4));
		scale.add(new Fretting(0, 4));
		scale.add(new Fretting(3, 5));
		scale.add(new Fretting(2, 5));
		scale.add(new Fretting(0, 5));
		m3scale.add(new FretPattern("major 3 note scale", scale, 6, new Note("G")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(7, 0));
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(3, 0));
		scale.add(new Fretting(7, 1));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(3, 1));
		scale.add(new Fretting(5, 2));
		scale.add(new Fretting(4, 2));
		scale.add(new Fretting(2, 2));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(4, 3));
		scale.add(new Fretting(2, 3));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(2, 4));
		scale.add(new Fretting(5, 5));
		scale.add(new Fretting(3, 5));
		scale.add(new Fretting(2, 5));
		m3scale.add(new FretPattern("major 3 note scale", scale, 7, new Note("G")));

		for(List<FretPattern> key : getAllKeys(m3scale))
			addModalBasesOfMajor(normalizePositions(key), "_three_note_scales", " 3 note scale");
	}

	private void addPentatonics() {
		boolean incmajor = prefs.getBoolean("pref_major_pentatonic_scales", false);
		boolean incminor = prefs.getBoolean("pref_minor_pentatonic_scales", false);
		if(!incmajor && !incminor) return;

		ArrayList<FretPattern> pentmajor = new ArrayList<FretPattern>();
		ArrayList<FretPattern> pentminor = new ArrayList<FretPattern>();
		ArrayList<Fretting> scale = new ArrayList<Fretting>();
		scale.add(new Fretting(3, 0));
		scale.add(new Fretting(0, 0));
		scale.add(new Fretting(2, 1));
		scale.add(new Fretting(0, 1));
		scale.add(new Fretting(2, 2));
		scale.add(new Fretting(0, 2));
		scale.add(new Fretting(2, 3));
		scale.add(new Fretting(0, 3));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(0, 4));
		scale.add(new Fretting(3, 5));
		scale.add(new Fretting(0, 5));
		if(incmajor) pentmajor.add(new FretPattern("major pentatonic scale", scale, 5, new Note("G")));
		if(incminor) pentminor.add(new FretPattern("minor pentatonic scale", scale, 1, new Note("E")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(3, 0));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(2, 1));
		scale.add(new Fretting(4, 2));
		scale.add(new Fretting(2, 2));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(2, 3));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(3, 4));
		scale.add(new Fretting(5, 5));
		scale.add(new Fretting(3, 5));
		if(incmajor) pentmajor.add(new FretPattern("major pentatonic scale", scale, 1, new Note("G")));
		if(incminor) pentminor.add(new FretPattern("minor pentatonic scale", scale, 2, new Note("E")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(7, 0));
		scale.add(new Fretting(5, 0));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(5, 1));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(4, 2));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(5, 3));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(5, 4));
		scale.add(new Fretting(7, 5));
		scale.add(new Fretting(5, 5));
		if(incmajor) pentmajor.add(new FretPattern("major pentatonic scale", scale, 2, new Note("G")));
		if(incminor) pentminor.add(new FretPattern("minor pentatonic scale", scale, 3, new Note("E")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(10, 0));
		scale.add(new Fretting(7, 0));
		scale.add(new Fretting(10, 1));
		scale.add(new Fretting(8, 1));
		scale.add(new Fretting(9, 2));
		scale.add(new Fretting(7, 2));
		scale.add(new Fretting(9, 3));
		scale.add(new Fretting(7, 3));
		scale.add(new Fretting(10, 4));
		scale.add(new Fretting(7, 4));
		scale.add(new Fretting(10, 5));
		scale.add(new Fretting(7, 5));
		if(incmajor) pentmajor.add(new FretPattern("major pentatonic scale", scale, 3, new Note("G")));
		if(incminor) pentminor.add(new FretPattern("minor pentatonic scale", scale, 4, new Note("E")));

		scale = new ArrayList<Fretting>();
		scale.add(new Fretting(12, 0));
		scale.add(new Fretting(10, 0));
		scale.add(new Fretting(12, 1));
		scale.add(new Fretting(10, 1));
		scale.add(new Fretting(12, 2));
		scale.add(new Fretting(9, 2));
		scale.add(new Fretting(12, 3));
		scale.add(new Fretting(9, 3));
		scale.add(new Fretting(12, 4));
		scale.add(new Fretting(10, 4));
		scale.add(new Fretting(12, 5));
		scale.add(new Fretting(10, 5));
		if(incmajor) pentmajor.add(new FretPattern("major pentatonic scale", scale, 4, new Note("G")));
		if(incminor) pentminor.add(new FretPattern("minor pentatonic scale", scale, 5, new Note("E")));

		if(incmajor)
		for(List<FretPattern> key : getAllKeys(pentmajor))
		for(FretPattern position : normalizePositions(key))
			patterns.add(position);

		if(incminor)
		for(List<FretPattern> key : getAllKeys(pentminor))
		for(FretPattern position : normalizePositions(key))
			patterns.add(position);
	}
}
