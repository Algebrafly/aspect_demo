package com.algebra.aspect.active.simple;

import com.algebra.aspect.active.conf.QueueSender;
import org.apache.activemq.command.ActiveMQQueue;
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

    /**
     * 使用封装工具类发送普通消息
     * @param destination 目标队列
     * @param message 消息内容
     */
    public void sendMessage2(String destination, String message) {
        queueSender.sendString(destination,message);
    }

    /**
     * 使用封装工具类发送延时消息
     * @param destination 目标队列
     * @param message 消息内容
     */
    public void sendMessage3(String destination, String message, long time) throws Exception {
        queueSender.sendStringWait(destination,message,time);
    }

    /**
     * 使用封装工具类发送普通消息
     * @param destinationStr 目标队列
     * @param message 消息内容
     */
    public void sendMessage4(String destinationStr, String message) {
        Destination destination = new ActiveMQQueue(destinationStr);
        queueSender.send(destination,message);
    }

    /**
     * 使用封装工具类发送延时消息
     * @param destinationStr 目标队列
     * @param message 消息内容
     */
    public void sendMessage5(String destinationStr, String message, long time) throws Exception {
        Destination destination = new ActiveMQQueue(destinationStr);
        queueSender.delaySend(destination,message,time);
    }

}
