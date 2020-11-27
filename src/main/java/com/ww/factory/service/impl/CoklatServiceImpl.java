package com.ww.factory.service.impl;

import java.util.*;
 
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.HandlerChain;
 
import com.ww.factory.model.*;
 
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
 
}