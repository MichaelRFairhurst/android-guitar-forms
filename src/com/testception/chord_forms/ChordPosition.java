package com.testception.chord_forms;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class ChordPosition {

	private static final String[] names = { "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E" };
	private int mynote;
	private int mytype;
	private int mynum;
	private ArrayList<Fretting> myfrets;

	public ChordPosition(Chord myposition, int note) {
		boolean octavedown = false;
		mynote = note;
		mynum = myposition.getNumber();
		mytype = myposition.getType();

		int i;
		for(Fretting f : myposition.getFrettings()) {
			if(f.getFret() + note > 24) {
				octavedown = true;
				break;
			}
		}

		myfrets = new ArrayList<Fretting>();
		for(Fretting f : myposition.getFrettings())
			myfrets.add(new Fretting(f.getFret() - (octavedown ? 12 : 0) + note, f.getString()));

		Collections.reverse(myfrets);
	}

	public List<Fretting> getFrettings() {
		return myfrets;
	}

	public String getName() {
		return String.valueOf(names[mynote]) + " " + (mytype == Chord.MAJOR ? "Major" : "Minor") + " Pos #" + String.valueOf(mynum);
	}
}
