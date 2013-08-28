package com.testception.fret_patterns;

import java.util.List;
import android.os.Bundle;
import android.os.Build;
import android.content.Intent;
import android.preference.PreferenceActivity;
import android.preference.PreferenceActivity.Header;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class PatternSettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		String action = getIntent().getAction();
		if(action != null && action.equals("SweepsFragment")) addPreferencesFromResource(R.xml.preference_sweeps);
		else if(action != null && action.equals("PositionsFragment")) addPreferencesFromResource(R.xml.preference_positions);
		else if(action != null && action.equals("PentatonicFragment")) addPreferencesFromResource(R.xml.preference_pentatonic);
		else if(action != null && action.equals("StandardFragment")) addPreferencesFromResource(R.xml.preference_standard);
		else if(action != null && action.equals("ThreeNoteFragment")) addPreferencesFromResource(R.xml.preference_three_note);
		else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) addPreferencesFromResource(R.xml.preference_headers_legacy);
    }

	// Called only on Honeycomb and later THIS IS A LIE
	@Override
	public void onBuildHeaders(List<Header> target) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) loadHeadersFromResource(R.xml.preference_headers, target);
	}

    static public class SweepsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle aSavedState) {
            super.onCreate(aSavedState);
            addPreferencesFromResource(R.xml.preference_sweeps);
        }
    }

    static public class StandardFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle aSavedState) {
            super.onCreate(aSavedState);
            addPreferencesFromResource(R.xml.preference_standard);
        }
    }

    static public class PentatonicFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle aSavedState) {
            super.onCreate(aSavedState);
            addPreferencesFromResource(R.xml.preference_pentatonic);
        }
    }

    static public class ThreeNoteFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle aSavedState) {
            super.onCreate(aSavedState);
            addPreferencesFromResource(R.xml.preference_three_note);
        }
    }

    static public class PositionsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle aSavedState) {
            super.onCreate(aSavedState);
            addPreferencesFromResource(R.xml.preference_positions);
        }
    }
}
