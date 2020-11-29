package com.ww.factory.service.impl;

import java.util.*;
 
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
 
import com.ww.factory.model.Coklat;
import com.ww.factory.model.ResepBahan;
import com.ww.factory.model.Request;
import com.ww.factory.handler.CoklatHandler;
 
@WebService
public class CoklatServiceImpl {
 
    @WebMethod
    public ArrayList<Coklat> getAllCoklatInFactory() {
        return new CoklatHandler().getAllCoklatInFactory();
    }

    @WebMethod
    public boolean addNewCoklat(Coklat coklat, ArrayList<ResepBahan> resepBahan) {
        return new CoklatHandler().addNewCoklat(coklat, resepBahan);
    }

    @WebMethod
    public boolean addStockCoklat(Request req) {
        return new CoklatHandler().addStockCoklat(req);
    }

    @WebMethod
    public ArrayList<ResepBahan> getResepBahan(int idcoklat) {
        return new CoklatHandler().getResepBahan(idcoklat);
    }
 
}