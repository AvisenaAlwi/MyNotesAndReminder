package com.picode.sena.mynotespapbprojectakhir;

public interface InterfaceTicker {
    /**
     * Method pada interface ini akan dipanggil setiap 1 detik sampai detik berakhir
     */
    void onTicker(int detik);

    /**
     * Method pada interface ini akan dipanggil ketika proses selesai
     */
    void onDone();
}
