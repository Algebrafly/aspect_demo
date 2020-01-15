package com.algebra.aspect.redis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/test01")
    @ApiOperation("test01")
    public String test01(){

        return "";
    }

}
