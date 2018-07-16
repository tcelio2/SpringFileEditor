package com.att.ub.utils;

public enum SessionValues {
        
    FILE_PATH("");
    
    private final String value;      

    private SessionValues(String value){
	
	this.value = value;
	
    }
    
    public String getValue() {
        return this.value;
    }
    
   
}
