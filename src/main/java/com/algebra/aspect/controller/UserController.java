package com.algebra.aspect.controller;

import com.algebra.aspect.domain.User;
import com.algebra.aspect.kafka.order.Order;
import com.algebra.aspect.kafka.order.OrderKafkaProvider;
import com.algebra.aspect.service.IUserService;
import com.algebra.aspect.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author al
 * @date 2019/6/11 9:21
 * @description
 */
@RestController
@Slf4j
@Api("userController | 用户操作类")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    OrderKafkaProvider orderKafkaProvider;

    @GetMapping("/getOneUserInfo")
    @ApiOperation(value = "getOneUserInfo")
    public WebApiResult<User> getUserInfoByParam(@RequestParam String userId){
        User u = userService.getUserInfoOne(userId,null);
        WebApiResult<User> result = new WebApiResult<>();
        result.setData(u);
        return result;
    }


    @RequestMapping(value = "/insertUser",method = RequestMethod.POST)
    @ApiOperation(value = "insertUser")
    public WebApiResult<Boolean> insertUser(@RequestBody User user){
        log.info(user.toString());
        userService.insertUserInfo(user);
        WebApiResult<Boolean> result = new WebApiResult<>();
        result.setData(true);
        return result;
    }

    @GetMapping("/selectUser")
    @ApiOperation(value = "selectUser")
    public WebApiResult<User> selectUser(@RequestParam String id){
        User u = userService.selectUserInfo(id);
        WebApiResult<User> result = new WebApiResult<>();
        result.setData(u);
        return result;
    }

    @GetMapping("/testKafkaOrder")
    @ApiOperation(value = "testKafkaOrder")
    public WebApiResult<String> testKafkaOrder(){
        WebApiResult<String> result = new WebApiResult<>();
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            Order order = Order.builder().orderId(0).orderNum(UUID.randomUUID().toString()).createTime(localDateTime).build();
            orderKafkaProvider.sendOrderMsg(order.getOrderId(),order.getOrderNum(),order.getCreateTime());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setErrorMessage("推送kafka异常");
        }
        return result;
    }


}
