package com.algebra.aspect.controller;

import com.algebra.aspect.domain.User;
import com.algebra.aspect.service.IUserService;
import com.algebra.aspect.util.WebApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getOneUserInfo")
    @ApiOperation(value = "getOneUserInfo")
    public WebApiResult<User> getUserInfoByParam(@RequestParam String userId){
        User u = userService.getUserInfoOne(userId,null);
        WebApiResult<User> result = new WebApiResult<>();
        result.setData(u);
        return result;
    }


}
