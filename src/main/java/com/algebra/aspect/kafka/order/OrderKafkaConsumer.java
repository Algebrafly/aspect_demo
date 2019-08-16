package com.algebra.aspect.kafka.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2019/8/16 15:26
 * @description
 */
@Component
@Slf4j
public class OrderKafkaConsumer {

    @KafkaListener(topics = KafkaTopics.ORDER_TOPIC, groupId = "algebra-01")
    public void orderConsumer(String msg){
        log.info("## consume message: {}",msg);
    }

}
