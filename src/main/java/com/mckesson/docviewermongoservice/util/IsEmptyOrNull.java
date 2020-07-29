package com.mckesson.docviewermongoservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IsEmptyOrNull {
	
	Logger logger = LoggerFactory.getLogger(IsEmptyOrNull.class);
	
    public boolean checkValue(String param) {
    	
    	if(param != null && !param.isEmpty()) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
}