package com.algebra.aspect.controller;

import com.algebra.aspect.active.conf.JmsConfig;
import com.algebra.aspect.active.conf.MessageModel;
import com.algebra.aspect.active.simple.JmsProducer;
import com.algebra.aspect.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("activeMq 测试专用")
public class ActiveMqTestController {

    @Autowired
    JmsProducer jmsProducer;

    @GetMapping("/activeMqNormalTest")
    @ApiOperation("普通消息发送测试")
    public WebApiResult<String> activeMqNormalTest(@RequestParam("msg") String msg){
        WebApiResult<String> result = new WebApiResult<>();
        try {
            jmsProducer.sendMessage2(JmsConfig.BATCH_TRADE_QUEUE,msg);
            jmsProducer.sendMessage4(JmsConfig.BATCH_QUERY_QUEUE,msg+"----->>");
        } catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("请求异常");
        }
        return result;
    }

    @GetMapping("/activeMqTimingTest")
    @ApiOperation("延时消息发送测试")
    public WebApiResult<String> activeMqTimingTest(@RequestParam("msg") String msg, @RequestParam("time") long time){
        WebApiResult<String> result = new WebApiResult<>();
        try {
            jmsProducer.sendMessage3(JmsConfig.BATCH_TRADE_QUEUE,msg,time);
            jmsProducer.sendMessage3("springboot.queue.test",msg+"123",time);
//            jmsProducer.sendMessage5(JmsConfig.BATCH_QUERY_QUEUE,msg+"----->>",time);
        } catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("请求异常");
        }
        return result;
    }

    @PostMapping("/activeMqTest")
    @ApiOperation("实现序列化的特殊消息类")
    public WebApiResult<String> activeMqTest(@RequestBody MessageModel msg, @RequestParam("time") long time){
        WebApiResult<String> result = new WebApiResult<>();
        try {
            jmsProducer.sendMessage5(JmsConfig.BATCH_QUERY_QUEUE,msg+"----->>",time);
        } catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("请求异常");
        }
        return result;
    }

}
