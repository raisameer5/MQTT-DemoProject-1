package com.daalchini.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UatConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UatConsumer.class);

    // UAT will only listen to uat. topics
    @JmsListener(destination = "uat.topic1", containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        logger.info("UAT Consumer received message from uat.topic1: {}", message);
    }
    
}

