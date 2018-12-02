package com.picode.sena.mynotespapbprojectakhir;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ketika activity dijalankan akan menampilkan fragment settings kita
        // Karena fragment settings kita extends dari PreferenceFragmentCompat
        // Desain xml nya gak perlu kita pusing pikirin
        // Cukup langsung commit SettingsFragment
        // BTW android.R.id.content itu ID bawaan dari android yang mereferensikan ke root view dari activity
        // Artinya nanti semua tampilan dalam activity ini akan diganti (replace) dengan fragment Settings
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
