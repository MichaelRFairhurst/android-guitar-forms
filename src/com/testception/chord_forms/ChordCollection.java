package com.testception.chord_forms;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

class ChordCollection {

	private List<Chord> chords = new ArrayList<Chord>();

	public ChordCollection() {
		ArrayList<Fretting> forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(3, 0));
		forchord.add(new Fretting(0, 0));
		forchord.add(new Fretting(0, 1));
		forchord.add(new Fretting(0, 2));
		forchord.add(new Fretting(2, 3));
		forchord.add(new Fretting(2, 4));
		forchord.add(new Fretting(3, 5));
		forchord.add(new Fretting(0, 5));
		chords.add(new Chord(Chord.MINOR, forchord, 1));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(3, 0));
		forchord.add(new Fretting(5, 1));
		forchord.add(new Fretting(4, 2));
		forchord.add(new Fretting(5, 3));
		forchord.add(new Fretting(2, 3));
		forchord.add(new Fretting(2, 4));
		forchord.add(new Fretting(3, 5));
		chords.add(new Chord(Chord.MINOR, forchord, 2));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(3, 0));
		forchord.add(new Fretting(5, 1));
		forchord.add(new Fretting(4, 2));
		forchord.add(new Fretting(5, 3));
		forchord.add(new Fretting(7, 4));
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(3, 5));
		chords.add(new Chord(Chord.MINOR, forchord, 3));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(8, 1));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(10, 4));
		forchord.add(new Fretting(7, 4));
		forchord.add(new Fretting(7, 5));
		chords.add(new Chord(Chord.MINOR, forchord, 4));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(12, 1));
		forchord.add(new Fretting(12, 2));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(10, 4));
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(7, 5));
		chords.add(new Chord(Chord.MINOR, forchord, 5));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(15, 0));
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(12, 1));
		forchord.add(new Fretting(12, 2));
		forchord.add(new Fretting(14, 3));
		forchord.add(new Fretting(14, 4));
		forchord.add(new Fretting(10, 4));
		forchord.add(new Fretting(12, 5));
		chords.add(new Chord(Chord.MINOR, forchord, 6));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(4, 0));
		forchord.add(new Fretting(0, 0));
		forchord.add(new Fretting(0, 1));
		forchord.add(new Fretting(1, 2));
		forchord.add(new Fretting(2, 3));
		forchord.add(new Fretting(2, 4));
		forchord.add(new Fretting(4, 5));
		forchord.add(new Fretting(0, 5));
		chords.add(new Chord(Chord.MAJOR, forchord, 1));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(4, 0));
		forchord.add(new Fretting(5, 1));
		forchord.add(new Fretting(4, 2));
		forchord.add(new Fretting(6, 3));
		forchord.add(new Fretting(2, 3));
		forchord.add(new Fretting(2, 4));
		forchord.add(new Fretting(4, 5));
		chords.add(new Chord(Chord.MAJOR, forchord, 2));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(4, 0));
		forchord.add(new Fretting(5, 1));
		forchord.add(new Fretting(4, 2));
		forchord.add(new Fretting(6, 3));
		forchord.add(new Fretting(7, 4));
		forchord.add(new Fretting(7, 5));
		forchord.add(new Fretting(4, 5));
		chords.add(new Chord(Chord.MAJOR, forchord, 3));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(7, 0));
		forchord.add(new Fretting(9, 1));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(11, 4));
		forchord.add(new Fretting(7, 4));
		forchord.add(new Fretting(7, 5));
		chords.add(new Chord(Chord.MAJOR, forchord, 4));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(12, 1));
		forchord.add(new Fretting(9, 1));
		forchord.add(new Fretting(9, 2));
		forchord.add(new Fretting(9, 3));
		forchord.add(new Fretting(11, 4));
		forchord.add(new Fretting(12, 5));
		forchord.add(new Fretting(7, 5));
		chords.add(new Chord(Chord.MAJOR, forchord, 5));

		forchord = new ArrayList<Fretting>();
		forchord.add(new Fretting(16, 0));
		forchord.add(new Fretting(12, 0));
		forchord.add(new Fretting(12, 1));
		forchord.add(new Fretting(13, 2));
		forchord.add(new Fretting(14, 3));
		forchord.add(new Fretting(14, 4));
		forchord.add(new Fretting(11, 4));
		forchord.add(new Fretting(12, 5));
		chords.add(new Chord(Chord.MAJOR, forchord, 6));
	}

	public ChordPosition getRandom(int type) {
		Random rand = new Random();
		return new ChordPosition(chords.get(rand.nextInt(chords.size())), rand.nextInt(11));
	}
}
