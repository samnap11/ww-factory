package com.ww.factory.service.impl;

import com.ww.factory.model.Saldo;
import com.ww.factory.model.SaldoHandler;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public class SaldoServiceImpl {
    @WebMethod
    public ArrayList<Saldo> getListOfSaldo(@WebParam(name = "jumlah") int jumlah) {
        return new SaldoHandler().getListOfSaldo(jumlah);
    }

    @WebMethod
    public int getCurrentSaldo() {
        return new SaldoHandler().getCurrentSaldo();
    }

    @WebMethod
    public boolean addSaldo(@WebParam(name = "newSaldo") int newSaldoInt) {
        return new SaldoHandler().addSaldo(newSaldoInt);
    }
}
