package com.picode.sena.mynotespapbprojectakhir;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentReminder extends Fragment {

    private FloatingActionButton fab;
    private RecyclerView rvReminder;
    private ArrayList<ModelReminder> data = new ArrayList<>();
    private AdapterRecyclerView adapterRecyclerView = new AdapterRecyclerView(data);

    /**
     * Method ketika view fragment akan dibuat
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout fragment_reminder menjadi variable v
        View v = inflater.inflate(R.layout.fragment_reminder, container, false);

        // Inisialisasi fab dari view v
        // Kenapa findViewById harus dari variable v ? karena v telah berisikan layout dari fragment_reminder
        fab = v.findViewById(R.id.floatingActionButton);
        rvReminder = v.findViewById(R.id.rv_reminder);

        // Set layout manager recyclerview secara linear
        rvReminder.setLayoutManager(new LinearLayoutManager(getContext()));
        // Tambahkan garis pemisah pada recyclerview untuk setiap itemnya
        rvReminder.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        // Lalu set adapter recycler view
        rvReminder.setAdapter(adapterRecyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ketika variable data kita tambah 1 data lagi
                // Maka data pada adapter akan bertambah, karena variable data pada class ini dengan variable
                // data pada class AdapterRecyclerView sama, karena keduanya adalah variable REFERENCE
                // Penjelasan : https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
                data.add(
                        new ModelReminder(getContext(), (int) System.currentTimeMillis(), 10)
                );
                // Lalu beritahu ke adapter bahwa ada 1 data lagi yang ditambahkan
                adapterRecyclerView.notifyItemInserted(adapterRecyclerView.getItemCount());
            }
        });

        return v;
    }
}
