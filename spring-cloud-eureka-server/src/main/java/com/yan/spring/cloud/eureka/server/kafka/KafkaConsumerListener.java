package com.yan.spring.cloud.eureka.server.kafka;

import org.springframework.stereotype.Component;

/**
 * Consumer
 *
 * @author : Y
 * @since 2023/5/5 20:22
 */
@Component
public class KafkaConsumerListener {

    @KafkaListener(topics = "$(kafka.topics")
    public void onMessage(String message) {
        System.out.println("Kafka消费者监听器，接受到消息:" + message);
    }


}