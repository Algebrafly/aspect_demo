package com.algebra.aspect.active.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author al
 * @date 2019/8/27 13:25
 * @description
 */
@Component
public class JmsProducer {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    public void sendMessage(Destination destination, String message) {
        this.jmsTemplate.convertAndSend(destination,message);
    }

}
