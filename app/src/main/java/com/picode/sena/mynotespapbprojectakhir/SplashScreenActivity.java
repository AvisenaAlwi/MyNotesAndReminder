package com.picode.sena.mynotespapbprojectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private final int TIMEOUT = 2000; // Timeout = 2 Detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kode untuk mendelay suatu kode yang ada pada method run di Runnable
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // Kode ini akan dieksekusi setelah menunggu { Timeout } detik
                        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                },
                TIMEOUT
        );
    }
}
