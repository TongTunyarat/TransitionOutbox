package com.example.outbox1.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name="outbox")
public class OutboxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "eventData",columnDefinition = "json")
    private String eventData;

    @Column(name = "status")
    private String status;

    @Column(name = "channel")
    private String channel;

    @Column(name = "channel",columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;
}

