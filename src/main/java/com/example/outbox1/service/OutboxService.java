package com.example.outbox1.service;
import com.example.outbox1.entity.OutboxEntity;
import com.example.outbox1.repository.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OutboxService {
    @Autowired
    private OutboxEventRepository outboxEventRepository;

    public void saveOutbox(String channel, String jsonData) {
        try {
            OutboxEntity outboxEntity = new OutboxEntity();
            outboxEntity.setStatus("PENDING");
            outboxEntity.setChannel(channel);
            outboxEntity.setEventData(jsonData);
            outboxEntity.setCreatedAt(LocalDateTime.now());

            outboxEventRepository.save(outboxEntity);
        } catch (DataAccessException e) {
            System.out.println("An error occurred while saving the data: " + e.getMessage());
            //outboxEventRepository.save(outboxEntity);
        }
    }
}
