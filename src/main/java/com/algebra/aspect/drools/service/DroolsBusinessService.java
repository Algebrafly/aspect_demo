package com.algebra.aspect.drools.service;

import java.util.Map;

/**
 * @author al
 * @date 2019/12/10 16:40
 * @description
 */
public interface DroolsBusinessService {
    /**
     * build所有规则
     */
    void buildAllRules();

    /**
     * build规则
     * @param fileName drl文件名
     * @param code
     */
    void buildRules(String fileName, String code);

    /**
     * 删除规则
     * @param fileName drl文件名
     */
    void deleteRules(String fileName);

    /**
     * 触发规则
     * @param eventParam
     */
//    List<RuleResult> fireRules(Map<String, Object> eventParam, String packageName);
}
