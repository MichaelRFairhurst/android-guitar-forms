package com.testception.fret_patterns;

import java.util.List;
import java.util.ArrayList;

class FretPattern {

	private static final String[] names = { "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E" };
	private String name;
	private List<Fretting> frettings;
	private int type;
	private int position;
	private int note;

	public FretPattern(String myname, List<Fretting> myfrettings, int myposition, int mynote) {
		frettings = myfrettings;
		name = myname;
		note = mynote;
		position = myposition;
	}

	public void setName(String mynewname) {
		name = mynewname;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int mynewposition) {
		position = mynewposition;
	}

	public List<Fretting> getFrettings() {
		return frettings;
	}

	public String getName() {
		return names[note] + " " + name + " Pos #" + position;
	}

	public FretPattern transpose(int interval) {
		ArrayList<Fretting> transposed = new ArrayList<Fretting>();
		boolean octavedown = true;
		for(Fretting f : frettings) {
			octavedown &= f.getFret() + interval > 11;
		}

		for(Fretting f : frettings) {
			transposed.add(f.transpose(octavedown ? interval - 12 : interval));
		}

		return new FretPattern(name, transposed, position, note + interval > 11 ? note + interval - 12 : note + interval);
	}
}
