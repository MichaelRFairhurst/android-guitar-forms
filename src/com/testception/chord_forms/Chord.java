package com.testception.chord_forms;

import java.util.List;
import java.util.ArrayList;

class Chord {
	public static final int MINOR = 0;
	public static final int MAJOR = 1;
	private List<Fretting> frettings;
	private int type;
	private int number;

	public Chord(int mytype, List<Fretting> myfrettings, int mynumber) {
		frettings = myfrettings;
		type = mytype;
		number = mynumber;
	}

	public int getNumber() {
		return number;
	}

	public List<Fretting> getFrettings() {
		return frettings;
	}

	public int getType() {
		return type;
	}

	public List<Fretting> getFrettingsForString(int string) {
		ArrayList<Fretting> response = new ArrayList<Fretting>();
		for(Fretting f : frettings) {
			if(f.getString() == string) response.add(f);
		}

		return response;
	}
}
