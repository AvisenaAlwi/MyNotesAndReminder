package com.picode.sena.mynotespapbprojectakhir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);

        // Set adapter
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        // Setup tab layout dengan view pager, sehingga tab layout bisa sinkron dnegan view pager
        tabLayout.setupWithViewPager(pager);
    }

    /**
     * Method ini dipaggil ketika activity akan membuat menu pada action bar
     *
     * @param menu : parameter menu variable referensi yang diberikan oleh activity
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Karena menu yang dimiliki oleh activity masih null
        // Maka kita perlu menginflasi { menu } dengan file menu xml kita
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Method override ini dipanggil ketika ada 1 item menu pada action bar yang di klik
     *
     * @param item : menu item yang di klik
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Karena ada kemungkinan dalam 1 menu terdapat banyak menu item
        // Maka kita perlu cek menu manakah yang diklik tersebut dengan identifikasi ID nya
        switch (item.getItemId()) {

            // Ketika item menu yang dilik adalah item menu seting maka akan masuk case ini
            case R.id.menu_settings:
                // Buka activity settings
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Nested class ViewPagerAdapter ini digunakan untuk adapter ViewPager
     */
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        /**
         * Konstruktor
         *
         * @param fm : SupportFragmentManager
         */
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Untuk mendapatkan fragment pada setiap posisi
         * Misal, posisi ke 0 maka fragment note
         * posisi ke 1 maka fragment lainnya
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentNote();
                case 1:
                    return new FragmentReminder();
            }
            return new FragmentNote();
        }


        /**
         * Untuk mendapatkan title pada masing masing fragment, yang nantinya ditampilkan pada
         * Tab Layout
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Notes";
                case 1:
                    return "Countdown Reminder";
            }
            return super.getPageTitle(position);
        }

        /**
         * Untuk menset berapa page dalam view pager tersebut
         *
         * @return
         */
        @Override
        public int getCount() {
            return 2;
        }
    }
}
