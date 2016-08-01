package com.med.brenda.exception;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class SessionTimeoutException extends Exception {
	
	private Logger logger = Logger.getLogger(SessionTimeoutException.class);
	 public SessionTimeoutException(String msg)  
	    {  
	        super(msg);  
	    }  
	 public SessionTimeoutException(){
	 }
}
