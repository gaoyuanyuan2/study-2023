package com.yan.spring.cloud.rocketmq.binder;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Binder
 *
 * @author : Y
 * @since 2023/5/31 21:28
 */
public class RocketMQMessageChannelBinder implements Binder<MessageChannel, ConsumerProperties, ProducerProperties> {

    private static final String GROUP = "test-group";
    private static final String TOPIC = "TEST_TOPIC";
    private static final String TAG = "TEST_TAG";
    private static final String NAME_ADDRESS = "localhost:9876";

    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inputChannel, ConsumerProperties consumerProperties) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(GROUP);
        consumer.setNamesrvAddr(NAME_ADDRESS);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try {
            consumer.subscribe(TOPIC, TAG);
            consumer.registerMessageListener((MessageListenerConcurrently) (messageExtList, context) -> {
                System.out.printf(Thread.currentThread().getName() + "Receive New Messages: " + messageExtList + " % n ");
                messageExtList.forEach(msg -> {
                    byte[] body = msg.getBody();
                    //发送消息到消息管道
                    inputChannel.send(new GenericMessage<Object>(body));
                });


                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (Exception e) {

        }
        return () -> {
            System.out.println(" consumer shutdown..");
            consumer.shutdown();
        };
    }

    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outputChannel, ProducerProperties producerProperties) {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer(GROUP);
        producer.setNamesrvAddr(NAME_ADDRESS);
        //Launch the instance.
        try {
            producer.start();
            SubscribableChannel subscribableChannel = (SubscribableChannel) outputChannel;
            //消息订阅回调
            subscribableChannel.subscribe(message -> {
                //消息主题
                Object messageBody = message.getPayload();
                Message mqMessage = new Message();
                mqMessage.setTopic(TOPIC);
                mqMessage.setTags(TAG);
                try {
                    mqMessage.setBody(serialize(message));
                    SendResult sendResult = producer.send(mqMessage);
                    System.out.printf("消息发送: %s%n", sendResult);
                } catch (Exception e) {

                }
            });
        } catch (Exception e) {

        }
        return () -> {
            System. out.println(" producer shutdown...");
            producer.shutdown();
        };
    }

    private byte[] serialize(Object serializable) throws IOException {
        if (serializable instanceof byte[]) {
            return (byte[]) serializable;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        //将Object写入字节流
        objectOutputStream.writeObject(serializable);
        //返回字节数组
        return outputStream.toByteArray();
    }

}