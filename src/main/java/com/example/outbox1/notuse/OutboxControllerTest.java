package com.example.outbox1.notuse;
import com.example.outbox1.repository.OutboxEventRepository;
import com.example.outbox1.service.OutboxService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@Transactional
@RestController
public class OutboxControllerTest {

    @Autowired
    private OutboxEventRepository outboxEventRepository;
    @Autowired
    private OutboxService outboxService;

    @PostMapping("/insertEvent")
    public String insertEvent() {
        outboxService.saveOutbox("C_Mark",
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
