package com.algebra.aspect.drools.service;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.model.KieModuleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * @author al
 * @date 2019/12/10 16:40
 * @description
 */
@Slf4j
public class DroolsBusinessServiceImpl implements DroolsBusinessService{

    @Value("${drl.path}")
    private String drlPath;

    /**
     * 加载规则目录
     */
    private final String BUILD_PATH = "src/main/resources/";

    /**
     * Kiebase
     */
    private final String KEI_BASE = "kieBase";

    /**
     * Kiebase
     */
    private final String SESSION = "session_";

    @Autowired
    private KieServices kieServices;

    @Autowired
    private KieFileSystem kieFileSystem;

    @Autowired
    private KieModuleModel kieModuleModel;

    @Autowired
    private KieRepository kieRepository;

    @Override
    public void buildAllRules() {
        log.info("Build rules start ...");
        File filePath = new File(drlPath);
        if(!filePath.exists()) {
            filePath.mkdir();
        }





    }

    @Override
    public void buildRules(String fileName, String code) {

    }

    @Override
    public void deleteRules(String fileName) {

    }
}
