package com.algebra.aspect.drools.config;

import lombok.extern.slf4j.Slf4j;
import org.drools.core.spi.KnowledgeHelper;

/**
 * @author al
 * @date 2019/12/26 16:27
 * @description drools 调试工具[打印日志]
 */
@Slf4j
public class Utility {
    public static void help(final KnowledgeHelper drools, final String message){
        log.info(message);
        log.info("rule triggered: " + drools.getRule().getName());
    }
    public static void helper(final KnowledgeHelper drools){
        log.info("rule triggered: " + drools.getRule().getName());
    }
}
