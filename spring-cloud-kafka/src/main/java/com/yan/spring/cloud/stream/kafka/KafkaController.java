package com.yan.spring.cloud.stream.kafka;

import com.yan.spring.cloud.stream.kafka.binder.MessageProducerBean;
import com.yan.spring.cloud.stream.kafka.binder.MyMessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * kafka
 *
 * @author : Y
 * @since 2023/5/5 20:18
 */
@RestController
public class KafkaController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;
    private MessageProducerBean messageProducerBean;
    private MyMessageProducerBean myMessageProducerBean;

    @Autowired
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate,
                           @Value("${kafka.topic}") String topic,
                           MessageProducerBean messageProducerBean,
                           MyMessageProducerBean myMessageProducerBean) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
        this.messageProducerBean = messageProducerBean;
        this.myMessageProducerBean = myMessageProducerBean;
    }

    @GetMapping(" /message/send")
    public Boolean sendMessage(@RequestParam String message) {
        kafkaTemplate.send(topic, message);
        return true;
    }

    @PostMapping(" /message/send")
    public Boolean sendBinderMessage(@RequestParam String message) {
        messageProducerBean.send(message);
        return true;
    }

    @PostMapping(" /message/send2mysource")
    public Boolean send2mysource(@RequestParam String message) {
        messageProducerBean.send(message);
        return true;
    }
}