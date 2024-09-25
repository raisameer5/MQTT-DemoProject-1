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

	
	 @JmsListener(destination = "dev.123", containerFactory = "jmsListenerContainerFactory")
	    public void listenToDevTopic(String message) {
	        logger.info("Received message from dev.123: {}", message);
	    }

	    // Listener for user.1223 topic
	    @JmsListener(destination = "user.123", containerFactory = "jmsListenerContainerFactory")
	    public void listenToUserTopic(String message) {
	        logger.info("Received message from user.1223: {}", message);
	    }

	    // Listener for system.123 topic (Admins only)
	    @JmsListener(destination = "system.123", containerFactory = "jmsListenerContainerFactory")
	    public void listenToSystemTopic(String message) {
	        logger.info("Received message from system.123: {}", message);
	    }
	    
	    }


