package com.algebra.aspect.active.conf;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * @author al
 * @date 2019/8/27 14:06
 * @description
 */
@Configuration
public class JmsConfig {

    public final static String BATCH_TRADE_QUEUE = "batch.trade";
    public final static String BATCH_QUERY_QUEUE = "batch.query";

    @Bean
    public Queue batchTradeQueue() {
        return new ActiveMQQueue(BATCH_TRADE_QUEUE);
    }

    @Bean
    public Queue batchQueryQueue() {
        return new ActiveMQQueue(BATCH_QUERY_QUEUE);
    }

    /**
     * queue模式的ListenerContainer
     * @param activeMqConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMqConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(activeMqConnectionFactory);
        return bean;
    }


}
