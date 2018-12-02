package com.picode.sena.mynotespapbprojectakhir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Class AdapterRecyclerView sebagai adapater recycler view yang ada pada fragment reminder
 * <p>
 * Lebih detail pada PPT PAPB-7 RecyclerViewAdapter
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    // PERHATIAN : setiap ada kode data.get(position) maka akan mengembalikan object
    // ModelReminder yang disimpan pada arraylist di index ke [position]

    // Lalu ketika ada kode data.get(position).xxxx() maka artinya program akan memanggil method
    // xxxx() pada class/object ModelReminder


    private ArrayList<ModelReminder> data;
    private Context context;

    public AdapterRecyclerView(ArrayList<ModelReminder> data) {
        this.data = data;
    }

    /**
     * Method ini dipanggil ketika membuat view dari fragment
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Set context dari viewgroup.getContext()
        this.context = viewGroup.getContext();

        // Inflate layout_item_reminder dan simpan sebagai variable view
        // view ini nantinya menjadi item dari recycler view
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_reminder, viewGroup, false);

        return new ViewHolder(view); // Lalu buat ViewHolder dengan variabel view
    }

    /**
     * Method onBindViewHolder ini digunakan untuk mengatur masing-masing item dalam recycler view
     * berdasarkan posisi / index item itu berada.
     * <p>
     * Jika item terdapat 10 maka method ini akan dipanggil 10 kali dan parameter position akan bernilai
     * 0 .. 9 dalam setiap pemanggilan. Misal pemanggilan pertama maka position = 0, kedua positon = 1
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // Inisialisasi nilai dari spinner dengan detik dari data index ke [position]
        int detik = data.get(position).getSecond();
        holder.spinnerDetik.setSelection(detik);
        // Berikan listener kepada spinner ketika item yang diselect berubah
        holder.spinnerDetik.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Method akan dipanggil ketika nilai pada spinner berubah
             * @param parent
             * @param view
             * @param pos
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Jika nilai spinner berubah maka lakukan perubahan juga pada data kita yang disimpan
                // pada variable arraylist data index ke [position]
                data.get(position).setSecond(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Listener ketika tombol mulai di klik
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Disable dahulu tombol mulai dan spinnernya sehingga tidak dapat digunakan sampai
                // proses selesai
                holder.btnStart.setEnabled(false);
                holder.spinnerDetik.setEnabled(false);

                // Lalu jalankan proses dengan method start
                // Method start membutuhkan argument InterfaceTicker
                // Maka kita perlu membuatnya, dengan cara Anonymouse class
                data.get(holder.getAdapterPosition()).start(

                        new InterfaceTicker() {

                            /**
                             * Method ini nantinya akan dipaggil setiap detik sampai selesai
                             * @param detik
                             */
                            @Override
                            public void onTicker(int detik) {
                                // KOMEN 1
                                holder.spinnerDetik.setSelection(detik);
                            }

                            /**
                             * Method ini nantinya dipanggil ketika proses sudah selesai
                             */
                            @Override
                            public void onDone() {
                                // KOMEN 2
                                holder.btnStart.setEnabled(true);
                                holder.spinnerDetik.setEnabled(true);
                            }
                        }
                );


            }
        });
    }

    /**
     * Untuk mendapatkan berapa item yang dimiliki oleh recyclerview
     *
     * @return
     */
    @Override
    public int getItemCount() {
        // Jumlahnya sama dengan jumlah data
        return data.size();
    }

    /**
     * Class view holder,
     * <p>
     * Lebih detail pada PPT PAPB-7 RecyclerViewAdapter
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        // Variable yang nantinya digunakan pada method onBindViewholder
        Spinner spinnerDetik;
        Button btnStart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inisialisasi kedua variable dengan cara cari view pada itemView
            // Itemview ini berasal dari view yang kita inflate pada method onCreateViewHolder
            // Cek method onCreateViewHolder() diatas
            spinnerDetik = itemView.findViewById(R.id.spinner_detik);
            btnStart = itemView.findViewById(R.id.btn_start);
        }
    }
}
