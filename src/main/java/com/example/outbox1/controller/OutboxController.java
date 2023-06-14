package com.example.outbox1.controller;

import com.example.outbox1.service.OutboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OutboxController {
    @Autowired
    private OutboxService outboxService;

    public String dataSaving() {
        outboxService.saveOutbox("test",
                "{\n" +
                        "  \"name\": \"John Doe\",\n" +
                        "  \"age\": 30,\n" +
                        "  \"email\": \"johndoe@example.com\",\n" +
                        "  \"address\": {\n" +
                        "    \"street\": \"123 Main St\",\n" +
                        "    \"city\": \"New York\",\n" +
                        "    \"country\": \"USA\"\n" +
                        "  },\n" +
                        "  \"hobbies\": [\"reading\", \"running\", \"traveling\"],\n" +
                        "  \"active\": true\n" +
                        "}\n"
        );
        return "Event inserted successfully";
    }
}
