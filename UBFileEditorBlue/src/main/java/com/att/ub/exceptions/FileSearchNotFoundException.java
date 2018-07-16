package com.att.ub.exceptions;

public class FileSearchNotFoundException extends Exception{
    
    public FileSearchNotFoundException(String message, Throwable t) {
	super(message,t);
    }
    
    public FileSearchNotFoundException(String message) {
	super(message);
    }
    
    public FileSearchNotFoundException() {
	super();
    }

    private static final long serialVersionUID = 1L;

}
