package com.algebra.aspect.drools;

import com.algebra.aspect.drools.fact.Address;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author al
 * @date 2019/11/7 17:24
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/drools")
@Api("drools测试类")
public class DroolsTestController {

    @Autowired
    KieSession kieSession;

    @GetMapping("/address/{postCode}")
    public void justForTest(@PathVariable(value = "postCode") String postCode){
        // 以下的数据可以从数据库来，这里写死了
        Address address = new Address();
        address.setPostcode(postCode);
        // 使用规则引擎
        kieSession.insert(address);
        int ruleFiredCount = kieSession.fireAllRules();
        log.info("触发了" + ruleFiredCount + "条规则");
        log.info("---------------------------------");
    }
}
