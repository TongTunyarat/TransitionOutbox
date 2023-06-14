package com.example.outbox1.repository;

import com.example.outbox1.entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OutboxEventRepository extends JpaRepository<OutboxEntity, Long> {
    List<OutboxEntity> findByStatus(String status);
    List<OutboxEntity> findByStatusIn(List<String> statuses);

}
