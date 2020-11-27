package com.ww.factory.model;

import java.util.*;

public class Bahan {
    private String nama;
    private int jumlah;
    private Date tanggalKadaluarsa;

    public Bahan() {

    };

    public Bahan(String _nama, int _jumlah, Date _tanggalKadaluarsa) {
        this.nama = _nama;
        this.jumlah = _jumlah;
        this.tanggalKadaluarsa = _tanggalKadaluarsa;
    }

    public String getNama() {
        return this.nama;
    }

    public int getJumlah() {
        return this.jumlah;
    }

    public Date getTanggalKadaluarsa() {
        return this.tanggalKadaluarsa;
    }

    public void setNama(String _nama) {
        this.nama = _nama;
    }

    public void setJumlah(int _jumlah) {
        this.jumlah = _jumlah;
    }

    public void setTanggalKadaluarsa(Date _tanggalKadaluarsa) {
        this.tanggalKadaluarsa = _tanggalKadaluarsa;
    }
}
