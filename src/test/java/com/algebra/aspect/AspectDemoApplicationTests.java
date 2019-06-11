package com.algebra.aspect;

import com.algebra.aspect.domain.User;
import com.algebra.aspect.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectDemoApplicationTests {

    @Autowired
    IUserService userService;

    @Test
    public void contextLoads() {
        Map<String,Object> param = new HashMap<>();
        param.put("uId","u_001");
        User u = userService.getUserInfoOne("u_001",param);
        System.out.println(u.toString());
    }

}
