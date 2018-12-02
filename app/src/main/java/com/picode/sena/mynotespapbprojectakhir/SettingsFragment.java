package com.picode.sena.mynotespapbprojectakhir;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        // Disini fragment ini akan menginflate xml yang kita buat
        // Lokasi filenya Res/xml/nama file.xml
        // Nama file kita preferences.xml
        // Panggil R.xml.preferences
        setPreferencesFromResource(R.xml.preferences, rootKey); // Root key ini diambil dari parameter rootKey
    }
}
