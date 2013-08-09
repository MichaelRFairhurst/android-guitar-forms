package com.testception.chord_forms;

class Fretting {

	public final static int STRING_LOW_E = 0;
	public final static int STRING_A = 1;
	public final static int STRING_D = 2;
	public final static int STRING_G = 3;
	public final static int STRING_B = 4;
	public final static int STRING_HIGH_E = 5;

	private final int string;
	private final int fret;

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

}
