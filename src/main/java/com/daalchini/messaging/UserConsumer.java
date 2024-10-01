package com.daalchini.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    // Listens to both user. and dev. topics
    @JmsListener(destination = "user.topic1", containerFactory = "jmsListenerContainerFactory")
    public void receiveUserMessage(String message) {
        logger.info("User Consumer received message from user.topic1: {}", message);
    }
}

