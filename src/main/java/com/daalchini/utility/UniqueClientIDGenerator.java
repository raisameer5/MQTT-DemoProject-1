package com.daalchini.utility;

import java.util.UUID;

public class UniqueClientIDGenerator {
	
	 public static String generateClientId() {
		 
	        return UUID.randomUUID().toString();
	        
	    }

}
