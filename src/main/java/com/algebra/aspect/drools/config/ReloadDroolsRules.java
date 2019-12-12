package com.algebra.aspect.drools.config;

import com.algebra.aspect.drools.util.KieContainerUtils;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author al
 * @date 2019/12/10 13:17
 * @description 从数据库读取的规则代码直接用字符串代替
 */
@Component
@Slf4j
public class ReloadDroolsRules {

    public void reload() throws UnsupportedEncodingException {
        KieServices kieServices = getKieServices();
        // 不能每次都kieServices.newKieFileSystem() 这样出来是不同的对象
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/temp.drl", loadRules());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            log.info(String.valueOf(results.getMessages()));
            throw new IllegalStateException("### errors ###");
        }

        KieContainerUtils.setKieContainer(kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId()));
        log.info("reload新规则重载成功");
    }

    /**
     * 从数据库中重新加载规则
     * @return string-规则文本
     */
    private String loadRules() {
        return "package rules;\n" +
                "dialect  \"java\"\n" +
                "\n" +
                "import com.algebra.aspect.drools.fact.Address\n" +
                "\n" +
                "rule \"校验邮编格式\"\n" +
                "    when\n" +
                "        $address :Address(postcode != null, postcode matches \"([0-9]{6})\")\n" +
                "    then\n" +
                "        System.out.println(\"[drools]邮政编码格式（6位）校验通过！---\");\n" +
                "end";

    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    public void reloadByHelper() throws UnsupportedEncodingException {

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(loadRules(), ResourceType.DRL);
//        kieHelper.addResource(new ClassPathResource("/aaa.drl"),ResourceType.DRL);

        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            log.info(String.valueOf(results.getMessages()));
            throw new IllegalStateException("### errors ###");
        }

//        KieBase kieBase = kieHelper.build();
        KieContainer kieContainer = kieHelper.getKieContainer();

        KieContainerUtils.setKieContainer(kieContainer);
        log.info("新规则重载成功");
    }
}
