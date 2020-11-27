package com.ww.factory.service.impl;

import com.ww.factory.model.Saldo;
import com.ww.factory.model.SaldoHandler;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public class SaldoServiceImpl {
    @WebMethod
    public ArrayList<Saldo> getListOfSaldo(int n) {
        return new SaldoHandler().getListOfSaldo(n);
    }

    @WebMethod
    public int getCurrentSaldo() {
        return new SaldoHandler().getCurrentSaldo();
    }

    @WebMethod
    public boolean addSaldo(int newSaldoInt) {
        return new SaldoHandler().addSaldo(newSaldoInt);
    }
}
