package com.algebra.aspect.active.conf;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2019/8/27 14:06
 * @description
 */
@Configuration
public class JmsConfig {

    public final static String BATCH_TRADE_QUEUE = "batch.trade.test";
    public final static String BATCH_QUERY_QUEUE = "batch.query.test";

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

    @Bean
    public ActiveMQConnectionFactory factory(@Value("${spring.activemq.broker-url}") String url){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        // 设置信任序列化包集合
        List<String> models = new ArrayList<>();
        models.add("java.lang");
        models.add("java.util");
        models.add("com.sky.frame.activemq");
        factory.setTrustedPackages(models);
        // 设置处理机制
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setMaximumRedeliveries(0); // 消息处理失败重新处理次数
        factory.setRedeliveryPolicy(redeliveryPolicy);
        return factory;
    }


}
