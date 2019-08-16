package com.algebra.aspect.kafka.order;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;

/**
 * @author al
 * @date 2019/8/16 15:17
 * @description
 */
@Component
@Slf4j
public class OrderKafkaProvider {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendOrderMsg(long orderId, String orderNum, LocalDateTime creatTime){

        // 构建实体
        Order order = Order.builder().orderId(orderId).orderNum(orderNum).createTime(creatTime).build();

        // 发送消息
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaTopics.ORDER_TOPIC, JSONObject.toJSONString(order));

        // 监听回调
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("## Send message fail ...");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("## Send message success ...");
            }
        });


    }


}
