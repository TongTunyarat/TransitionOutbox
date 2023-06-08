package com.example.outbox1.TestController;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage() {
        rabbitTemplate.convertAndSend("C_Tong", "message");
    }
}

