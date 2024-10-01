package com.daalchini.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daalchini.messaging.Producer;

@RestController
@RequestMapping("/api")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private Producer producer;


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String topic, @RequestBody String message) {

        try {
            producer.sendMessage(topic, message);
            logger.info("Message successfully sent to topic '{}'", topic);
            return ResponseEntity.ok("Message sent to topic " + topic);
        } catch (Exception e) {
            logger.error("Error sending message to topic '{}': {}", topic, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message to topic " + topic);
        }
    }
}
