package com.picode.sena.mynotespapbprojectakhir;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentNote extends Fragment {

    private static final String PREF_KEY_NOTES = "notes";
    private EditText editTextNote;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);
        editTextNote = rootView.findViewById(R.id.edit_text_note);

        // Load text/note sebelumnya dari SharedPreferences
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String notes = pref.getString(PREF_KEY_NOTES, "");

        // Set text editTextNote dengan variable notes
        editTextNote.setText(notes);

        // Listener ketika text pada editTextNote berubah
        editTextNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /**
             * Simpan text ke SharePreference ketika selesai berubah
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                SharedPreferences.Editor pref = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                pref.putString(PREF_KEY_NOTES, s.toString());
                pref.apply();
            }
        });
        return rootView;
    }
}
