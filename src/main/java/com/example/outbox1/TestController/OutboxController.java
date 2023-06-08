package com.example.outbox1.TestController;

import com.example.outbox1.Entity.OutboxEntity;
import com.example.outbox1.Repository.OutboxEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.webservices.client.HttpWebServiceMessageSenderBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class OutboxController {
    private final OutboxEventRepository outboxEventRepository;
    private final HttpWebServiceMessageSenderBuilder messageSenderBuilder;

    public OutboxController(OutboxEventRepository outboxEventRepository, HttpWebServiceMessageSenderBuilder messageSenderBuilder) {
        this.outboxEventRepository = outboxEventRepository;
        this.messageSenderBuilder = messageSenderBuilder;
    }

    public void insertEvent() {
        OutboxEntity event = new OutboxEntity();
        event.setStatus("PENDING");
        event.setChannel("C_Tong");
        event.setEventData("{\"key\":\"value\"}}");
        event.setCreatedAt(LocalDateTime.now());

        outboxEventRepository.save(event);
    }
}
