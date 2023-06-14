package com.example.outbox1.service;

import com.example.outbox1.entity.OutboxEntity;
import com.example.outbox1.repository.OutboxEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OutboxEventRepository outboxEventRepository;

    private static final int MAX_RETRIES = 3;

    public void sendMessageToRabbitMQ() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<OutboxEntity> pendingEntities = outboxEventRepository.findByStatusIn(Arrays.asList("PENDING", "FAILED"));

        try {
            for (OutboxEntity entity : pendingEntities) {
                int retryCount = 0;
                boolean sentSuccessfully = false;

                while (retryCount < MAX_RETRIES && !sentSuccessfully) {
                    try {
                        String jsonData = objectMapper.writeValueAsString(entity);

                        String routingKey = entity.getChannel();

                        rabbitTemplate.convertAndSend(routingKey, jsonData);

                        entity.setStatus("SENT");
                        outboxEventRepository.save(entity);

                        System.out.println("Message sent to RabbitMQ successfully. ID: " + entity.getId());

                        sentSuccessfully = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Failed to send message to RabbitMQ. Retrying...");
                        System.out.println("Error: " + e.getMessage());

                        Thread.sleep(5000);
                    }

                    retryCount++;
                }

                if (!sentSuccessfully) {
                    entity.setStatus("FAILED");
                    outboxEventRepository.save(entity);

                    System.out.println("Failed to send message to RabbitMQ after multiple retries. ID: " + entity.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
