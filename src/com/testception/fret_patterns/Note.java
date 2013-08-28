package com.testception.fret_patterns;

class Note {

	private static final String[] names = { "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E" };
	private final int index;

	public Note(String name) {
		index = getIndexByName(name);
	}

	private int getIndexByName(String name) {
		int myindex = 0;
		for(int i = 0; i < names.length; i++)
		if(names[i] == name)
			myindex = i;

		return myindex;
	}

	protected Note(int myindex) {
		index = myindex;
	}

	private int normalize(int testindex) {
		if(testindex < 0) return testindex + 12;
		if(testindex > 11) return testindex - 12;
		return testindex;
	}

	public String getName() {
		return names[index];
	}

	public Note transpose(int interval) {
		return new Note(normalize(index + interval));
	}

	public int getInterval(Note other) {
		return normalize(getIndexByName(other.getName()) - index);
	}

}
