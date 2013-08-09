package com.testception.chord_forms;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class ChordCollection {

	private List<Chord> chords = new ArrayList<Chord>();

	public ChordCollection() {
		ArrayList<Fretting> forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(3, 5));
		forchord.add(new Fretting(0, 5));
		forchord.add(new Fretting(0, 4));
		forchord.add(new Fretting(0, 3));
		forchord.add(new Fretting(2, 2));
		forchord.add(new Fretting(2, 1));
		forchord.add(new Fretting(3, 0));
		forchord.add(new Fretting(0, 0));
		chords.add(new Chord(Chord.MINOR, forchord, 1));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(3, 5));
		forchord.add(new Fretting(5, 4));
		forchord.add(new Fretting(4, 3));
		forchord.add(new Fretting(5, 2));
		forchord.add(new Fretting(2, 2));
		forchord.add(new Fretting(2, 1));
		forchord.add(new Fretting(3, 0));
		chords.add(new Chord(Chord.MINOR, forchord, 2));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(3, 5));
		forchord.add(new Fretting(5, 4));
		forchord.add(new Fretting(4, 3));
		forchord.add(new Fretting(5, 2));
		forchord.add(new Fretting(7, 1));
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(3, 0));
		chords.add(new Chord(Chord.MINOR, forchord, 3));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(8, 4));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(10, 1));
		forchord.add(new Fretting(7, 1));
		forchord.add(new Fretting(7, 0));
		chords.add(new Chord(Chord.MINOR, forchord, 4));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(12, 4));
		forchord.add(new Fretting(12, 3));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(10, 1));
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(7, 0));
		chords.add(new Chord(Chord.MINOR, forchord, 5));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(15, 5));
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(12, 4));
		forchord.add(new Fretting(12, 3));
		forchord.add(new Fretting(14, 2));
		forchord.add(new Fretting(14, 1));
		forchord.add(new Fretting(10, 1));
		forchord.add(new Fretting(12, 0));
		chords.add(new Chord(Chord.MINOR, forchord, 6));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(4, 5));
		forchord.add(new Fretting(0, 5));
		forchord.add(new Fretting(0, 4));
		forchord.add(new Fretting(1, 3));
		forchord.add(new Fretting(2, 2));
		forchord.add(new Fretting(2, 1));
		forchord.add(new Fretting(4, 0));
		forchord.add(new Fretting(0, 0));
		chords.add(new Chord(Chord.MAJOR, forchord, 1));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(4, 5));
		forchord.add(new Fretting(5, 4));
		forchord.add(new Fretting(4, 3));
		forchord.add(new Fretting(6, 2));
		forchord.add(new Fretting(2, 2));
		forchord.add(new Fretting(2, 1));
		forchord.add(new Fretting(4, 0));
		chords.add(new Chord(Chord.MAJOR, forchord, 2));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(4, 5));
		forchord.add(new Fretting(5, 4));
		forchord.add(new Fretting(4, 3));
		forchord.add(new Fretting(6, 2));
		forchord.add(new Fretting(7, 1));
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(4, 0));
		chords.add(new Chord(Chord.MAJOR, forchord, 3));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(9, 4));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(11, 1));
		forchord.add(new Fretting(7, 1));
		forchord.add(new Fretting(7, 0));
		chords.add(new Chord(Chord.MAJOR, forchord, 4));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(12, 4));
		forchord.add(new Fretting(9, 4));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(11, 1));
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(7, 0));
		chords.add(new Chord(Chord.MAJOR, forchord, 5));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(16, 5));
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(12, 4));
		forchord.add(new Fretting(13, 3));
		forchord.add(new Fretting(14, 2));
		forchord.add(new Fretting(14, 1));
		forchord.add(new Fretting(11, 1));
		forchord.add(new Fretting(12, 0));
		chords.add(new Chord(Chord.MAJOR, forchord, 6));
	}

	public ChordPosition getRandom(int type) {
		Random rand = new Random();
		return new ChordPosition(chords.get(rand.nextInt(chords.size())), rand.nextInt(11));
	}
}
