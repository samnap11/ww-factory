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
 
}