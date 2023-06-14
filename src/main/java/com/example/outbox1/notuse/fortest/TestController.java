//package com.example.outbox1.config.rabbitmq;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/test")
//public class TestController {
//
//    @Autowired
//    private QueueSender queueSender;
//
//    @GetMapping
//    public String send(){
//        queueSender.send("test message");
//        return "ok. done";
//    }
//
//}
