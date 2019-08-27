package com.algebra.aspect.active.simple;

import com.algebra.aspect.active.conf.JmsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author al
 * @date 2019/8/27 13:26
 * @description
 */
@Component
@Slf4j
public class JmsConsumer {

    @JmsListener(destination = "springboot.queue.test")
    public void receiveSimpleQueue(String msg) {
        log.info("接收到消息：{}",msg);
    }

    @JmsListener(destination = JmsConfig.BATCH_TRADE_QUEUE, containerFactory = "jmsListenerContainerQueue")
    public void onQueueMessage(String msg) {
        log.info("接收到消息：{}",msg);
    }
}
