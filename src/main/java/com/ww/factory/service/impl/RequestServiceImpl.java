package com.ww.factory.service.impl;

import java.util.*;
 
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.HandlerChain;
 
import com.ww.factory.model.*;

@WebService
public class RequestServiceImpl {

    @WebMethod
    public ArrayList<Request> getAllRequest() {
        return new RequestHandler().getAllRequest();
    }

    @WebMethod
    public boolean addNewRequest(int idcoklat, int jumlah) {
        return new RequestHandler().addNewRequest(idcoklat, jumlah);
    }

    @WebMethod
    public String getRequestStatus(Request req) { 
        return new RequestHandler().getRequestStatus(req);
    }

    @WebMethod
    public boolean updateRequestStatus(Request req) { 
        return new RequestHandler().updateRequestStatus(req);
    }


}