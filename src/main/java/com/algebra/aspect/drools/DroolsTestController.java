package com.algebra.aspect.drools;

import com.algebra.aspect.drools.config.ReloadDroolsRules;
import com.algebra.aspect.drools.fact.Address;
import com.algebra.aspect.drools.fact.Phone;
import com.algebra.aspect.drools.util.KieContainerUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

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

    @Autowired
    private ReloadDroolsRules reload;

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

    @GetMapping("/address2/{postCode}")
    public void justForTest2(@PathVariable(value = "postCode") String postCode){
        KieContainer kieContainer = KieContainerUtils.getKieContainer();
        KieSession kieSession1 = kieContainer.newKieSession();

        // 以下的数据可以从数据库来，这里写死了
        Address address = new Address();
        address.setPostcode(postCode);
        // 使用规则引擎
        kieSession1.insert(address);
        int ruleFiredCount = kieSession1.fireAllRules();
        log.info("触发了" + ruleFiredCount + "条规则");
        log.info("---------------------------------");
    }

    @GetMapping("/address3")
    public void justForTest3(@RequestParam(value = "postCode") String postCode,@RequestParam(value = "phoneNo") String phoneNo){
        KieContainer kieContainer = KieContainerUtils.getKieContainer();
        KieSession kieSession1 = kieContainer.newKieSession();

        // 以下的数据可以从数据库来，这里写死了
        Address address = new Address();
        address.setPostcode(postCode);
        Phone phone = new Phone();
        phone.setPhoneNo(phoneNo);
        // 使用规则引擎
        kieSession1.insert(phone);
        kieSession1.insert(address);
        int ruleFiredCount = kieSession1.fireAllRules();
        log.info("触发了" + ruleFiredCount + "条规则");
        log.info("---------------------------------");
    }

    @GetMapping("/reload")
    public String reload() throws IOException {
        reload.reload();
        return "ok";
    }
}
