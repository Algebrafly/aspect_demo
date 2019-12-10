package com.algebra.aspect.drools.util;

import org.kie.api.runtime.KieContainer;

/**
 * @author al
 * @date 2019/12/10 13:09
 * @description Kies 工具类
 */
public class KieUtils {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
    }

}
