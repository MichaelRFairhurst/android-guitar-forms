package com.testception.fret_patterns;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

class FretPattern {

	private static final String[] names = { "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E" };
	private String name;
	private List<Fretting> frettings;
	private int type;
	private int position;
	private Note root;

	public FretPattern(String myname, List<Fretting> myfrettings, int myposition, Note myroot) {
		frettings = myfrettings;
		name = myname;
		root = myroot;
		position = myposition;
	}

	public void setRoot(Note mynewroot) {
		root = mynewroot;
	}

	public Note getRoot() {
		return root;
	}

	public void setName(String mynewname) {
		name = mynewname;
	}

	public void setPosition(int mynewposition) {
		position = mynewposition;
	}

	public int getPosition() {
		return position;
	}

	public List<Fretting> getFrettings() {
		return frettings;
	}

	public String getName() {
		return root.getName() + " " + name + " Pos #" + position;
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

		return new FretPattern(name, transposed, position, root.transpose(interval));
	}

	public int getLowestFret() {
		int lowestfret = 24;
		for(Fretting f : frettings) {
			lowestfret = Math.min(lowestfret, f.getFret());
		}
		return lowestfret;
	}
}
