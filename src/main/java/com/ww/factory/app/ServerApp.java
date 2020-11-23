package com.ww.factory.app;
 
import javax.xml.ws.Endpoint;
 
import com.ww.factory.service.impl.HelloWorldServiceImpl;
 
public class ServerApp {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/factory", new HelloWorldServiceImpl());
        System.out.println("Factory Services Started!");
    }
}