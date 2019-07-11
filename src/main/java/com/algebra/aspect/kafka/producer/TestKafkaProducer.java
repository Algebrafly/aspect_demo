package com.algebra.aspect.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author al
 * @date 2019/7/11 9:13
 * @description
 */
@Slf4j
@RestController
public class TestKafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg") String msg){
        ListenableFuture<SendResult<String, String>> mytopic = kafkaTemplate.send("my_topic", msg);
        SendResult<String, String> sendResult = null;
        try {
            sendResult = mytopic.get();

            ProducerRecord<String, String> record = sendResult.getProducerRecord();
            log.info("生产者接受到的结果：{}",record);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return sendResult==null?"":sendResult.toString();
    }


}
