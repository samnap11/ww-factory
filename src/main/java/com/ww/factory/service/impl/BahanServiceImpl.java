package com.ww.factory.service.impl;

import java.util.*;
 
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.HandlerChain;
 
import com.ww.factory.model.*;
 
@WebService
@HandlerChain(file = "handler-chain.xml")
public class BahanServiceImpl {
 
    @WebMethod
    public ArrayList<Bahan> getAllBahanInFactory() {
        return new BahanHandler().getAllBahanInFactory();
    }
 
}