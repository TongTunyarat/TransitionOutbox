package com.example.outbox1.Repository;

import com.example.outbox1.Entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface OutboxEventRepository extends JpaRepository<OutboxEntity, Long> {
    List<OutboxEntity> findByStatus(String status);
}
