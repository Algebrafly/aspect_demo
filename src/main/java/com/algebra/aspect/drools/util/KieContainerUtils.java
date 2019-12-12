package com.algebra.aspect.drools.util;

import org.kie.api.runtime.KieContainer;

/**
 * @author al
 * @date 2019/12/10 13:09
 * @description Kies 容器工具类 - 设置规则容器
 */
public class KieContainerUtils {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {
        KieContainerUtils.kieContainer = kieContainer;
    }

}
