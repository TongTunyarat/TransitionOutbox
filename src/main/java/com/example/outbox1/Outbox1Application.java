package com.example.outbox1;

import com.example.outbox1.controller.OutboxController;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableScheduling
@SpringBootApplication
public class Outbox1Application {

	public static void main(String[] args) {
		SpringApplication.run(Outbox1Application.class, args);
	}

	@Autowired
	private OutboxController outboxController;

	@PostConstruct
	public void runScheduleDataSaving() {
		String result = outboxController.dataSaving();
		System.out.println(result);
	}

}

