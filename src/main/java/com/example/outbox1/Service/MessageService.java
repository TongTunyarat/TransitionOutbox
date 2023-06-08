package com.example.outbox1.Service;

import com.example.outbox1.Entity.OutboxEntity;
import com.example.outbox1.Repository.OutboxEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final RabbitTemplate rabbitTemplate;
    private final OutboxEventRepository outboxEventRepository;

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate, OutboxEventRepository outboxEventRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.outboxEventRepository = outboxEventRepository;
    }

    public void sendMessageToRabbitMQ() {
        // Retrieve JSON data from the database with status = "PENDING"
        List<OutboxEntity> pendingEntities = outboxEventRepository.findByStatus("PENDING");

        try {
            for (OutboxEntity entity : pendingEntities) {
                // Convert data to JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(entity);

                // Publish JSON message to RabbitMQ
                rabbitTemplate.convertAndSend("dataChapter1", entity.getChannel(), jsonData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
