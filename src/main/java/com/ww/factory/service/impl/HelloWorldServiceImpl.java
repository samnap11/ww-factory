package com.ww.factory.service.impl;
 
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.HandlerChain;
 
import com.ww.factory.model.TopNews;
 
@WebService
// @HandlerChain(file = "handler-chain.xml")
public class HelloWorldServiceImpl {
 
    @WebMethod
    public String hello(@WebParam(name = "name") String name) {
        return "Hello " + name + "!";
    }
 
    @WebMethod
    public TopNews getTopNews() {
        TopNews tn = new TopNews();
        tn.setHighlights("Mary Zheng published an example for Jax-ws tutorial.");
        tn.setTitle("Jax-WS Tutorial is Available");
        return tn;
    }
 
}