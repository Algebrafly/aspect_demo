package com.algebra.aspect.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2019/7/11 9:19
 * @description
 */
@Component
@Slf4j
public class TestKafkaConsumer {

    @KafkaListener(topics = {"my_topic"})
    public void testListener(ConsumerRecord<?, ?> record){
        log.info("topic = {}, offset = {}, value = {} \n", record.topic(), record.offset(), record.value());
    }

}
