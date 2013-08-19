package com.testception.fret_patterns;

class Fretting {

	private final int string;
	private final int fret;
	private static final String[] names = { "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E" };

	public Fretting(int myfret, int mystring) {
		string = mystring;
		fret = myfret;
	}

	public int getString() {
		return string;
	}

	public int getFret() {
		return fret;
	}

	public Fretting transpose(int interval) {
		return new Fretting(fret + interval, string);
	}

}
