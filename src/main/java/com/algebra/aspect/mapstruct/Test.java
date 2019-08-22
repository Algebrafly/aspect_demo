package com.algebra.aspect.mapstruct;

import com.algebra.aspect.mapstruct.dto.UserDto;
import com.algebra.aspect.mapstruct.dtomapper.UserDtoMapper;
import com.algebra.aspect.mapstruct.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author al
 * @date 2019/8/22 16:10
 * @description
 */
@RestController("/user")
@Slf4j
@Api("mapStruct | DO与VO转换测试")
public class Test {

    @Autowired
    UserDtoMapper userDtoMapper;

    @PostMapping("/getUser")
    @ApiOperation(value = "getUser")
    public void getUser(@RequestBody UserDto userDto){
        User user = userDtoMapper.dtoToUser(userDto);
        log.info(user.toString());
    }


    @PostMapping("/getUserDto")
    @ApiOperation(value = "getUserDto")
    public void getUserDto(@RequestBody User user){
        log.info("转换前："+user.toString());
        UserDto userDto = userDtoMapper.userToDto(user);
        log.info("转换后："+userDto.toString());
    }

}
