package com.att.ub.exceptions;

public class CopyBookNotFoundException extends Exception {


    public CopyBookNotFoundException(String message, Throwable t) {
	super(message,t);
    }
    
    public CopyBookNotFoundException(String message) {
	super(message);
    }
    
    public CopyBookNotFoundException() {
	super();
    }

    private static final long serialVersionUID = 1L;

}
