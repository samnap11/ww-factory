package com.ww.factory.model;

import javax.xml.bind.annotation.*;

@XmlType(propOrder={"nama", "jumlah"})
public class ResepBahan {
    private String namaBahan;
    private int jumlahBahan;

    public ResepBahan() {}

    public ResepBahan(String _namaBahan, int _jumlahBahan) {
        this.namaBahan   = _namaBahan;
        this.jumlahBahan = _jumlahBahan;
    }

    public void setNamaBahan(String _namaBahan) {
        this.namaBahan = _namaBahan;
    }

    public void setJumlahBahan(int _jumlahBahan) {
        this.jumlahBahan = _jumlahBahan;
    }

    public String getNamaBahan()   { return this.namaBahan; }
    public int getJumlahBahan() { return this.jumlahBahan; }
}