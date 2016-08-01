package com.med.brenda.exception;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class BusinessException extends Exception {
	private Logger logger = Logger.getLogger(BusinessException.class);
	 public BusinessException(String msg)  
	    {  
	        super(msg);  
	    }  
	 public BusinessException(){
		 
	 }
}
