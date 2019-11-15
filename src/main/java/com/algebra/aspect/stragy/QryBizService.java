package com.algebra.aspect.stragy;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2019/11/15 17:40
 * @description 业务逻辑执行服务（相当于out_service）
 */
@Component
public class QryBizService {

    /*
     * 对于一个接扣多实现类的情景，可以使用集合来承接，spring会自动创建实体并且注入到IOC容器中
     */
    @Autowired
    private List<QryProcessor> qryProcessorList;
    @Autowired
    private Map<String,QryProcessor> qryProcessorMap;

    public Map<String,String> query(String type){
        Map<String,String> request = Maps.newHashMap();
        Map<String,String> result = Maps.newHashMap();

        if(StringUtils.isNotBlank(type)){
            request.put("type",type);
        }

        // list-foreach
        qryProcessorList.forEach(f -> {
            if(f.check(request,result)){
                f.handle(request,result);
            }
        });
        return result;
    }

    public Map<String,String> queryMap(String type){
        Map<String,String> request = Maps.newHashMap();
        Map<String,String> result = Maps.newHashMap();

        if(StringUtils.isNotBlank(type)){
            request.put("type",type);
        }

        // map-foreach
        qryProcessorMap.forEach((k,qryProcessor)->{
            if(qryProcessor.check(request,result)){
                qryProcessor.handle(request,result);
            }
        });

        return result;
    }

}
