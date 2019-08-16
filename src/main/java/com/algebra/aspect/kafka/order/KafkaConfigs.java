package com.algebra.aspect.kafka.order;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author al
 * @date 2019/8/16 15:28
 * @description
 */
@Configuration
@Data
public class KafkaConfigs {

    @Value("${spring.kafka.consumer.group-id}")
    public String groupId;

}
