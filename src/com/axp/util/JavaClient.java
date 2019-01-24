package com.axp.util;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.axp.thrift.TTGService;

public class JavaClient {
	
		  
	    public void startClient() {  
	        TTransport transport;  
	        try {  
	            transport = new TSocket("115.159.34.109", 9099);  
	            TProtocol protocol = new TBinaryProtocol(transport);  
	            TTGService.Client  client = new TTGService.Client (protocol);  
	            transport.open();  
	           String a=  client.add_user( "wqm" , "123" , "axp");
	            transport.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
	    }  
	  
	    public static void main(String[] args) {  
	    	JavaClient client = new JavaClient();  
	        client.startClient();  
	    }  
	 
	}