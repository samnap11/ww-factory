package com.ww.factory.model;

import java.sql.Timestamp;

public class Saldo {
    private int saldo;
    private Timestamp saldo_timestamp;

    public Saldo() {
        this.saldo = 0;
        this.saldo_timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Saldo(int saldo) {
        this.saldo = saldo;
        this.saldo_timestamp = new Timestamp(System.currentTimeMillis());
    }

    public int getSaldo() {
        return saldo;
    }

    public Timestamp getTimestamp() {
        return saldo_timestamp;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setTimestamp(Timestamp saldo_timestamp) {
        this.saldo_timestamp = saldo_timestamp;
    }
}
