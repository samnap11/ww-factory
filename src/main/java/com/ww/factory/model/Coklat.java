package com.ww.factory.model;

import java.util.*;

public class Coklat {
    private String nama;
    private int jumlah;
    private int ID;
    // private ArrayList<ResepBahan> resep;

    public Coklat() {
        // this.resep = new ArrayList<ResepBahan>();
    };

    public Coklat(String _nama, int _jumlah, int _ID) {
        this.nama = _nama;
        this.jumlah = _jumlah;
        this.ID = _ID;
        // this.resep = new ArrayList<ResepBahan>();
    }

    public String getNama() {
        return this.nama;
    }

    public int getJumlah() {
        return this.jumlah;
    }

    public int getID() {
        return this.ID;
    }

    // public ArrayList<ResepBahan> getResep() {
    //     return this.resep;
    // }

    public void setNama(String _nama) {
        this.nama = _nama;
    }

    public void setJumlah(int _jumlah) {
        this.jumlah = _jumlah;
    }

    public void setID(int _ID) {
        this.ID = _ID;
    }
}