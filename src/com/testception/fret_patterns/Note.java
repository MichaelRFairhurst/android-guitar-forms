package com.testception.fret_patterns;

class Note {

	private static final String[] names = { "E", "F", "F#/Gb", "G", "G#/Ab", "A", "A#/Bb", "B", "C", "C#/Db", "D", "D#/Eb", "E" };
	private final int index;

	public Note(String name) {
		int myindex = 0;
		for(int i = 0; i < names.length; i++)
		if(names[i] == name)
			myindex = i;

		index = myindex;
	}

	protected Note(int myindex) {
		index = myindex;
	}

	public String getName() {
		return names[index];
	}

	public Note transpose(int interval) {
		return new Note(index + interval > 11 ? index + interval - 12 : index + interval);
	}

}
