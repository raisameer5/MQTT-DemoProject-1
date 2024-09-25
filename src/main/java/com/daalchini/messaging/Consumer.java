package com.daalchini.messaging;

import java.util.UUID;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.daalchini.utility.UniqueClientIDGenerator;

@Component
public class Consumer {
	
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	
	  @JmsListener(destination = "test-queue", containerFactory = "jmsListenerContainerFactory")
	    public void listen(String message) {
	      
	        logger.info("Received message: {}", message);
	  
	        logger.info("Consumer with client ID: {} is active", UniqueClientIDGenerator.generateClientId());
	    }
	    
	    }


