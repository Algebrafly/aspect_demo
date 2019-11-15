package com.algebra.aspect.stragy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author al
 * @date 2019/11/15 17:30
 * @description B-服务实现逻辑
 */
@Component
public class QryBProcessor implements QryProcessor {
    @Override
    public boolean check(Map<String, String> request, Map<String, String> result) {
        return StringUtils.equals(request.get("type"), "qryBProcessor") || Objects.isNull(request.get("type"));
    }

    @Override
    public void handle(Map<String, String> request, Map<String, String> result) {
        result.put("B","item_B");
    }
}
