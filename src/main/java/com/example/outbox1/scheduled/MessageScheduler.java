package com.example.outbox1.scheduled;
import com.example.outbox1.controller.OutboxController;
import com.example.outbox1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

    @Autowired
    private MessageService messageService;

    @Autowired
    private OutboxController outboxController;

    @Scheduled(fixedDelay = 10000)
    public void scheduleMessageSending() {
        messageService.sendMessageToRabbitMQ();
    }


}
