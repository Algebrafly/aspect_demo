package com.algebra.aspect.active.conf;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.core.JmsMessagingTemplate;
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

    /**
     * 发送消息
     * @param destination destination是发送到的队列
     * @param message message是待发送的消息
     */
    public <T extends Serializable> void send(Destination destination, T message){
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 延时发送
     * @param destination 发送的队列
     * @param data 发送的消息
     * @param time 延迟时间
     */
    public <T extends Serializable> void delaySend(Destination destination, T data, Long time){
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        // 获取连接工厂
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        try {
            // 获取连接
            connection = connectionFactory.createConnection();
            connection.start();
            // 获取session，true开启事务，false关闭事务
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            producer = session.createProducer(destination);
            producer.setDeliveryMode(JmsProperties.DeliveryMode.PERSISTENT.getValue());
            ObjectMessage message = session.createObjectMessage(data);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            // 发送消息
            producer.send(message);
            session.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (producer != null){
                    producer.close();
                }
                if (session != null){
                    session.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
