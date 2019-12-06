package com.algebra.aspect.controller;

import com.algebra.aspect.annotation.AccessLimit;
import com.algebra.aspect.util.WebApiResult;
import com.algebra.aspect.util.conf.RedisUtil;
import com.algebra.generic.entity.ext.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author al
 * @date 2019/12/6 14:07
 * @description redis 测试
 */
@RestController
@RequestMapping("/redis")
@Slf4j
@Api("redis 测试")
public class RedisController {

    private static int ExpireTime = 600;   // redis中存储的过期时间600s

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/redisSet")
    @ApiOperation("redisSet")
    // 限制一分钟内只能访问三次
    @AccessLimit(seconds = 60,maxCount = 3)
    public boolean redisSet(@RequestParam("key") String key, @RequestParam("value")String value){

        Person p = Person.builder().name("tom").age(18).birthday(new Date()).salary(new BigDecimal(8888)).build();

        return redisUtil.set(key,p,ExpireTime);
//        return redisUtil.set(key,value);
    }

    @GetMapping("redisGet")
    @ApiOperation("redisGet")
    public WebApiResult<Person> redisGet(@RequestParam("key")String key){
        WebApiResult<Person> result = new WebApiResult<>();
        result.setData((Person) redisUtil.get(key));
        return result;
    }

    @GetMapping("expire")
    @ApiOperation("expire")
    public boolean expire(@RequestParam("key")String key){
        return redisUtil.expire(key,ExpireTime);
    }


}
