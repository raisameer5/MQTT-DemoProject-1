package com.daalchini.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DevConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DevConsumer.class);


    @JmsListener(destination = "dev.topic1", containerFactory = "jmsListenerContainerFactory")
    public void receiveDevMessage(String message) {
        logger.info("Dev Consumer received message from dev.topic1: {}", message);
    }
    
}

