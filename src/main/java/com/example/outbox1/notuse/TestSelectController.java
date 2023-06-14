package com.example.outbox1.notuse;

import com.example.outbox1.entity.OutboxEntity;
import com.example.outbox1.repository.OutboxEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestSelectController {

    @Autowired
    private OutboxEventRepository outboxEventRepository;

    @GetMapping("/get-data")
    public List<OutboxEntity> getAllData() {
        return this.outboxEventRepository.findAll();
    }
}

