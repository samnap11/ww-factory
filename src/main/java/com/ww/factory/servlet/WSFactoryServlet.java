package com.ww.factory.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WSFactory",
          urlPatterns = {"/tot"},
          loadOnStartup = 1)
public class WSFactoryServlet extends HttpServlet {

    // @WebServiceRef(wsdlLocation = "localhost:8080/ws-factory/bahan?wsdl")
    // private BahanServiceImpl service;

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        System.out.println("Masuk servlet get...");
        // PrintWriter writer = res.getWriter();

    }

    @Override 
    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        System.out.println("Masuk servlet post...");
        res.setContentType("text/xml");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept, Authorization, User-Agent");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        res.addHeader("Access-Control-Max-Age", "86400");

    }

    @Override 
    protected void doOptions (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        System.out.println("Masuk servlet option...");
        res.setContentType("text/xml");
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept, Authorization, User-Agent");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        res.addHeader("Access-Control-Max-Age", "86400");

    }

}