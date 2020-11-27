package com.ww.factory.service.impl;

import java.util.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.ww.factory.model.*;

@WebService
public class BahanServiceImpl {

    @WebMethod
    public ArrayList<Bahan> getAllBahanInFactory() {
        return new BahanHandler().getAllBahanInFactory();
    }

    @WebMethod
    public boolean addBahan(@WebParam(name = "nama") String nama, @WebParam(name = "jumlah") int jumlah, @WebParam(name = "tanggalKadaluarsa") String tanggalKadaluarsa) {
        return new BahanHandler().addBahan(nama, jumlah, tanggalKadaluarsa);
    }

}
