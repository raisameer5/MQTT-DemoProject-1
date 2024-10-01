package com.daalchini.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import com.daalchini.utility.UniqueClientIDGenerator;

public class ActiveMQClient {

    private final String BROKER_URL = "tcp://localhost:61616"; 
    private final String BROKER_USERNAME = "admin";  
    private final String BROKER_PASSWORD = "admin";  

    // 1. Define the ActiveMQConnectionFactory bean to connect to the ActiveMQ broker
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);           
        connectionFactory.setUserName(BROKER_USERNAME);        
        connectionFactory.setPassword(BROKER_PASSWORD);  
        return connectionFactory;
    }

    // 2. Wrap the ActiveMQConnectionFactory with a SingleConnectionFactory to support clientId
    @Bean
    public SingleConnectionFactory singleConnectionFactory() {
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(connectionFactory());
        singleConnectionFactory.setClientId(UniqueClientIDGenerator.generateClientId()); // Set the clientID here
        return singleConnectionFactory;
    }

    // 3. Use SingleConnectionFactory for JmsTemplate to ensure clientId is applied
    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(singleConnectionFactory());  // Updated to use singleConnectionFactory
    }

    // 4. Use SingleConnectionFactory for DefaultJmsListenerContainerFactory as well
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(singleConnectionFactory());  // Updated to use singleConnectionFactory
        factory.setSubscriptionDurable(true); 
        return factory;
    }
}
