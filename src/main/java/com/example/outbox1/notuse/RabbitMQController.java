package com.example.outbox1.notuse;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.outbox1.service.MessageService;

import java.util.logging.Logger;

@Component
@Slf4j
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageService messageService;

    public void sendMessage() {
        log.info("Sending message to RabbitMQ...");
        rabbitTemplate.convertAndSend("test", "message");
        log.info("Message sent successfully!");
    }
}

