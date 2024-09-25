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
    public String sendMessage(@RequestBody MessageRequest messageRequest) {
        // Send to the specified topic
        producer.sendMessage(messageRequest.getDestination(), messageRequest.getMessage());
        return "Message sent: " + messageRequest.getMessage();
    }

    public static class MessageRequest {
        private String destination;
        private String message;

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}