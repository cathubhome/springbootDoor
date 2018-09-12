package com.ciwr.controller.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created with IDEA
 * Author:catHome
 * Description: kafka消费者
 * Time:Create on 2018/9/12 22:03
 */
@Component
@Slf4j
public class Consumer {

    @KafkaListener(topics = "${kafka.test.topic}")
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<String> topic = Optional.ofNullable(record.topic());
        Optional<?> key = Optional.ofNullable(record.key());
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        log.debug("kafka receive topic-{},key-{},message-{}",topic.get(),key.get(), kafkaMessage.get());
    }
}
