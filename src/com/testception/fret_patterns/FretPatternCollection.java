package com.testception.fret_patterns;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import android.content.SharedPreferences;

class FretPatternCollection {

	private List<FretPattern> patterns = new ArrayList<FretPattern>();
	private SharedPreferences prefs;

	public FretPatternCollection(SharedPreferences myprefs) {

		prefs = myprefs;
		addMajorBasedScales();
		addThreeNoteMajor();

		if(prefs.getBoolean("pref_eight_note_minor_sweeps", false)) {
			ArrayList<Fretting> chord = new ArrayList<Fretting>();
			chord.add(new Fretting(3, 0));
			chord.add(new Fretting(0, 0));
			chord.add(new Fretting(0, 1));
			chord.add(new Fretting(0, 2));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(3, 5));
			chord.add(new Fretting(0, 5));
			patterns.add(new FretPattern("minor 8 note sweep", chord, 1, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(3, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(5, 3));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(3, 5));
			patterns.add(new FretPattern("minor 8 note sweep", chord, 2, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(3, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(5, 3));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			chord.add(new Fretting(3, 5));
			patterns.add(new FretPattern("minor 8 note sweep", chord, 3, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(8, 1));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(10, 4));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			patterns.add(new FretPattern("minor 8 note sweep", chord, 4, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(12, 2));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(10, 4));
			chord.add(new Fretting(12, 5));
			chord.add(new Fretting(7, 5));
			patterns.add(new FretPattern("minor 8 note sweep", chord, 5, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(15, 0));
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(12, 2));
			chord.add(new Fretting(14, 3));
			chord.add(new Fretting(14, 4));
			chord.add(new Fretting(10, 4));
			chord.add(new Fretting(12, 5));
			patterns.add(new FretPattern("minor 8 note sweep", chord, 6, 0));
		}

		if(prefs.getBoolean("pref_eight_note_major_sweeps", false)) {
			ArrayList<Fretting> chord = new ArrayList<Fretting>();
			chord.add(new Fretting(4, 0));
			chord.add(new Fretting(0, 0));
			chord.add(new Fretting(0, 1));
			chord.add(new Fretting(1, 2));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(4, 5));
			chord.add(new Fretting(0, 5));
			patterns.add(new FretPattern("major 8 note sweep", chord, 1, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(4, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(6, 3));
			chord.add(new Fretting(2, 3));
			chord.add(new Fretting(2, 4));
			chord.add(new Fretting(4, 5));
			patterns.add(new FretPattern("major 8 note sweep", chord, 2, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(4, 0));
			chord.add(new Fretting(5, 1));
			chord.add(new Fretting(4, 2));
			chord.add(new Fretting(6, 3));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			chord.add(new Fretting(4, 5));
			patterns.add(new FretPattern("major 8 note sweep", chord, 3, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(7, 0));
			chord.add(new Fretting(9, 1));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(11, 4));
			chord.add(new Fretting(7, 4));
			chord.add(new Fretting(7, 5));
			patterns.add(new FretPattern("major 8 note sweep", chord, 4, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(9, 1));
			chord.add(new Fretting(9, 2));
			chord.add(new Fretting(9, 3));
			chord.add(new Fretting(11, 4));
			chord.add(new Fretting(12, 5));
			chord.add(new Fretting(7, 5));
			patterns.add(new FretPattern("major 8 note sweep", chord, 5, 0));

			chord = new ArrayList<Fretting>();
			chord.add(new Fretting(16, 0));
			chord.add(new Fretting(12, 0));
			chord.add(new Fretting(12, 1));
			chord.add(new Fretting(13, 2));
			chord.add(new Fretting(14, 3));
			chord.add(new Fretting(14, 4));
			chord.add(new Fretting(11, 4));
			chord.add(new Fretting(12, 5));
			patterns.add(new FretPattern("major 8 note sweep", chord, 6, 0));
		}
	}

	public FretPattern getRandom(int type) {
		if(patterns.size() == 0) return new FretPattern("None selected", new ArrayList<Fretting>(), 0, 0);
		int transpose = 0;
		Random rand = new Random();

		if(prefs.getBoolean("pref_rand_root_notes", true)) {
			transpose = rand.nextInt(11);
		}

		return patterns.get(rand.nextInt(patterns.size())).transpose(transpose);
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
		mscales.add(new FretPattern("major scale", scale, 1, 1));

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
		mscales.add(new FretPattern("major scale", scale, 2, 1));

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
		mscales.add(new FretPattern("major scale", scale, 3, 1));

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
		mscales.add(new FretPattern("major scale", scale, 4, 1));

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
		mscales.add(new FretPattern("major scale", scale, 5, 1));

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
		mscales.add(new FretPattern("major scale", scale, 6, 1));

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
		mscales.add(new FretPattern("major scale", scale, 7, 1));

		addModalBasesOfMajor(mscales, "_scales", " scale");
	}

	private void addThreeNoteMajor() {
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
		m3scale.add(new FretPattern("major 3 note scale", scale, 1, 3));

		m3scale = new ArrayList<FretPattern>();
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
		m3scale.add(new FretPattern("major 3 note scale", scale, 2, 3));

		m3scale = new ArrayList<FretPattern>();
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
		m3scale.add(new FretPattern("major 3 note scale", scale, 3, 3));

		m3scale = new ArrayList<FretPattern>();
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
		m3scale.add(new FretPattern("major 3 note scale", scale, 4, 3));

		m3scale = new ArrayList<FretPattern>();
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
		m3scale.add(new FretPattern("major 3 note scale", scale, 5, 3));

		m3scale = new ArrayList<FretPattern>();
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
		m3scale.add(new FretPattern("major 3 note scale", scale, 6, 3));

		m3scale = new ArrayList<FretPattern>();
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
		m3scale.add(new FretPattern("major 3 note scale", scale, 7, 3));

		addModalBasesOfMajor(m3scale, "_three_note_scale", " 3 note scale");
	}

	private void addModalBasesOfMajor(ArrayList<FretPattern> mscales, String prefsuffix, String namesuffix) {
		String[] modes = { "dorian", "phrygian", "lydian", "mixolydian", "minor", "locrian" };

		for(FretPattern major : mscales) {
			if(prefs.getBoolean("pref_major" + prefsuffix, true)) patterns.add(major);
			for(int i = 0; i < modes.length; i++) {
				if(prefs.getBoolean("pref_" + modes[i] + prefsuffix, false)) {
					FretPattern mode = major.transpose(0);
					int newposition = major.getPosition() + 6 - i;
					mode.setPosition(newposition > 7 ? newposition - 7 : newposition);
					mode.setName(modes[i] + namesuffix);
					patterns.add(mode);
				}
			}
		}
	}
}
