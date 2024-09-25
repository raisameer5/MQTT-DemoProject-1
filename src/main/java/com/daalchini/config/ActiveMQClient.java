package com.daalchini.config;

import org.apache.activemq.ActiveMQConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import com.daalchini.utility.UniqueClientIDGenerator;


    public class ActiveMQClient {
    	
        private final String BROKER_URL = "tcp://localhost:61616"; 
        private final String BROKER_USERNAME = "admin";  
        private final String BROKER_PASSWORD = "admin";  
       

        
        @Bean
        public ActiveMQConnectionFactory connectionFactory() {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setBrokerURL(BROKER_URL);           
            connectionFactory.setUserName(BROKER_USERNAME);        
            connectionFactory.setPassword(BROKER_PASSWORD);  
            
            return connectionFactory;
        }
        
        @Bean
        public SingleConnectionFactory singleConnectionFactory() {
            SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(connectionFactory());
            singleConnectionFactory.setClientId(UniqueClientIDGenerator.generateClientId()); // Set the clientID here
            return singleConnectionFactory;
        }


       
        @Bean
        public JmsTemplate jmsTemplate() {
          
            return new JmsTemplate(singleConnectionFactory()); 
        }

        @Bean
        public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            
            factory.setConnectionFactory(singleConnectionFactory());
            factory.setClientId(UniqueClientIDGenerator.generateClientId());
            factory.setSubscriptionDurable(true); 
            return factory;
        }
      
}

