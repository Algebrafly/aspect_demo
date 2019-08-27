package com.algebra.aspect.active.conf;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.Serializable;

/**
 * @author al
 * @desc mq消息生产者
 */
@Component("queueSender")
public class QueueSender {

    @Autowired
    private JmsMessagingTemplate jmsTemplate;
    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 发送普通消息
     * @param queueName 队列名
     * @param message 消息内容
     */
    public void sendString(String queueName, final String message) {
        jmsTemplate.convertAndSend(new ActiveMQQueue(queueName),message);
    }

    /**
     * 发送定时消息
     * @param queueName 队列名
     * @param messageString 消息内容
     * @param time 延时时间
     * @throws Exception exp
     */
    public void sendStringWait(String queueName,String messageString,long time) throws Exception {
        // 通过工厂创建一个连接
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建一个session会话 事务 自动ack
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        // 创建一个消息队列
        Destination destination = session.createQueue(queueName);
        // 创建生产者
        MessageProducer producer = session.createProducer(destination);
        // 消息持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        TextMessage message = session.createTextMessage(messageString);
        long period = 10 * 1000;// 每次10s
        int repeat = 0;// 重复发送次数
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
        message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);
        // 发送消息
        producer.send(message);
        session.commit();
        producer.close();
        session.close();
        connection.close();
    }
}
