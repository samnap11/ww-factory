package com.mkyong.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
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

public class CORSHandler implements SOAPHandler<SOAPMessageContext>{
    @Override
    public boolean handleMessage(SOAPMessageContext context) {

      boolean response= ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue(); 

      if (response) {

          Map<String, List<String>> headers = (Map<String, List<String>>) context.get(MessageContext.HTTP_RESPONSE_HEADERS);
          if (null == headers) {

              headers = new HashMap<String, List<String>>();
          }

          headers.put("Operation",Collections.singletonList("something");
          headers.put("Access-Control-Allow-Origin", "*");
          headers.put("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
          headers.put("Access-Control-Allow-Credentials", "true");
          headers.put("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        }
      return true;
    }
}