package com.algebra.aspect.active.simple;

import com.algebra.aspect.active.conf.JmsConfig;
import com.algebra.aspect.active.conf.QueueSender;
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

    @Autowired
    QueueSender queueSender;

    public void sendMessage(Destination destination, String message) {
        this.jmsTemplate.convertAndSend(destination,message);
    }

    public void sendMessage2(String destination, String message) {
        queueSender.sendString(destination,message);
    }

    public void sendMessage3(String destination, String message) throws Exception {
        long time = 10000;
        queueSender.sendStringWait(destination,message,time);
    }

}
