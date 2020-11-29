package com.ww.factory.service.impl;

import com.ww.factory.handler.UserHandler;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class UserServiceImpl {
    @WebMethod
    public boolean Register(@WebParam(name = "username") String username, @WebParam(name = "email") String email, @WebParam(name = "password") String unhashed_password) {
        return new UserHandler().Register(username, email, unhashed_password);
    }

    @WebMethod
    public boolean Login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        return new UserHandler().Login(username, password);
    }
}
