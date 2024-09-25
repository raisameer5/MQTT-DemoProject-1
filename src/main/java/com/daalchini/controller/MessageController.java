package com.daalchini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daalchini.messaging.Producer;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private Producer producer;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
    	
        producer.sendMessage("test-topic", message);
        return "Message sent: " + message;
    }
}