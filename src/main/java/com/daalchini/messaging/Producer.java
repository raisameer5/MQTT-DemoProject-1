package com.daalchini.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	 private static final Logger logger = LoggerFactory.getLogger(Producer.class);

	
	 @Autowired
	    private JmsTemplate jmsTemplate;

	 public void sendMessage(String destination, String message) {
	        logger.info("Sending message: {} to destination: {}", message, destination);
	        jmsTemplate.convertAndSend(destination, message);
	        logger.info("Message sent successfully");
	    }
	        
	    

}
