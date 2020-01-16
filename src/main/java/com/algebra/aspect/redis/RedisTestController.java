package com.algebra.aspect.redis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2020/1/15 17:37
 * @description
 */
@RestController
@Api("redis测试")
public class RedisTestController {

    @Autowired
    DistributedRedisLock distributedRedisLock;

    @GetMapping("/test01")
    @ApiOperation("test01")
    public String test01(String isRelease){
        // 加锁
        distributedRedisLock.acquire("test");

        System.out.println("正在使用锁！！");

        // doSth.
        distributedRedisLock.release("test");
        return "ok";
    }

}
