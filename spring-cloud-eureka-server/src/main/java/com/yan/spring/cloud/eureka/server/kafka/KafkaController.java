package com.yan.spring.cloud.eureka.server.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * kafka
 *
 * @author : Y
 * @since 2023/5/5 20:18
 */
public class KafkaController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate,
                           @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @PostMapping(" /message/send")
    public Boolean sendMessage(@RequestParam String message) {
        kafkaTemplate.send(topic, message);
        return true;
    }
}