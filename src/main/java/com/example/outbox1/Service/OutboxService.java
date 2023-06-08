package com.example.outbox1.Service;
import com.example.outbox1.Entity.OutboxEntity;
import com.example.outbox1.Repository.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

@Service
public class OutboxService {
    private final OutboxEventRepository outboxEventRepository;

    @Autowired
    public OutboxService(OutboxEventRepository outboxEventRepository) {
        this.outboxEventRepository = outboxEventRepository;
    }

    public void insertEvent() {
        try {
            File jsonFile = new ClassPathResource("DataToSend/data.json").getFile();
            ObjectMapper objectMapper = new ObjectMapper();
            OutboxEntity[] entities = objectMapper.readValue(jsonFile, OutboxEntity[].class);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the channel: ");
            String channel = scanner.nextLine();

            for (OutboxEntity entity : entities) {
                entity.setStatus("PENDING");
                entity.setChannel(channel);
                entity.setCreatedAt(LocalDateTime.now());
                outboxEventRepository.save(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
