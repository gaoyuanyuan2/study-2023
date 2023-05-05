package com.yan.spring.cloud.eureka.server.kafka;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

import java.util.Properties;

/**
 * Kafka API
 *
 * @author : Y
 * @since 2023/5/5 20:02
 */
public class APIKafkaService {
    public static void main(String[] args) {
        //初始化配置
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        //创建Kafka Producer
        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        String topic = "topic";
        Integer partition = 0;
        Long timestamp = System.currentTimeMillis();
        String key = "message-key";
        String value = "message-value";
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>(topic, partition, timestamp, key, value);
        //发放Kakfa 消息
        Future<RecordMetadata> metadataFuture = kafkaProducer.send(record);
        //强制执行
        metadataFuture.get();
    }
}