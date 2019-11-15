package com.algebra.aspect.stragy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author al
 * @date 2019/11/15 17:45
 * @description
 */
@RestController
public class SampleController {

    @Autowired
    QryBizService qryBizServicer;

    @GetMapping("/query1")
    public Map<String,String> query(String type){
        return qryBizServicer.query(type);
    }

    @GetMapping("/query2")
    public Map<String,String> queryMap(String type){
        return qryBizServicer.queryMap(type);
    }

}
