package com.ww.factory.handler;

import java.io.IOException;
import java.util.*;
// import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;
import javax.servlet.http.HttpServletResponse;

public class CORSHandler implements SOAPHandler<SOAPMessageContext>{

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean response = ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue(); 
        // HandlerUtils.printMessageContext("Service SOAPHandler", context);
        System.out.println("TESUTOOOOOO>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>!!!!!!!!!!!!!!!!!!!.");
        if (response) {
            HttpServletResponse res = (HttpServletResponse) context.get(MessageContext.SERVLET_RESPONSE);
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept, Authorization, User-Agent");
            res.addHeader("Access-Control-Allow-Credentials", "true");
            res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
            res.addHeader("Access-Control-Max-Age", "86400");
        }
        return true;
    }

    // @Override
    // public boolean handleMessage(SOAPMessageContext context) {

    //   boolean response= ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue(); 

    //   if (response) {
    //       Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_RESPONSE_HEADERS);
    //       if (null == headers) {
    //           headers = new HashMap<String, List<String>>();
    //       }
    //     //   headers.put("Operation", Collections.singletonList("something"));
    //       headers.put("Access-Control-Allow-Origin", Collections.singletonList("*"));
    //       headers.put("Access-Control-Allow-Headers", Arrays.asList("origin", "content-type", "accept", "authorization"));
    //       headers.put("Access-Control-Allow-Credentials", Collections.singletonList("true"));
    //       headers.put("Access-Control-Allow-Methods", Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
    //       System.out.println("euy");

    //       for (Map.Entry<String, List<String>> entry : headers.entrySet())  {
    //         System.out.println("Key = " + entry.getKey());
    //         for (int i = 0; i < entry.getValue().size(); i++) {
    //             System.out.println(entry.getValue().get(i));
    //         } 
    //       }

    //     }

    //   return true;
    // }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("Server : handleFault()......");
        return true;
    }

    @Override
    public void close(MessageContext context) {
        System.out.println("Server : close()......");
    }

    @Override
    public Set<QName> getHeaders() {
        System.out.println("Server : getHeaders()......");
        return null;
    }
}