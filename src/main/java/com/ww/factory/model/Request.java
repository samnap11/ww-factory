package com.ww.factory.model;

public class Request {

    private int idrequest;
    private int idcoklat;
    private int jumlah;
    private String status;

    public Request() {

    }
    public Request(int _idrequest, int _idcoklat, int _jumlah, String _status) {
        this.idrequest = _idrequest;
        this.idcoklat = _idcoklat;
        this.jumlah = _jumlah;
        this.status = _status;
    }

    public int getIdRequest() {
        return this.idrequest;
    }

    public int getIdCoklat() {
        return this.idcoklat;
    }

    public int getJumlah() {
        return this.jumlah;
    }

    public String getStatus() {
        return this.status;
    }

    public void setIdRequest(int _idrequest) {
        this.idrequest = _idrequest;
    }

    public void setIdCoklat(int _idcoklat) {
        this.idcoklat = _idcoklat;
    }

    public void setJumlah(int _jumlah) {
        this.jumlah = _jumlah;
    }

    public void setStatus(String _status) {
        this.status = _status;
    }
}