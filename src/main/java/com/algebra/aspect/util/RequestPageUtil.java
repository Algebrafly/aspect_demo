package com.algebra.aspect.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author al
 * @date 2020/1/7 14:10
 * @description 分页交互参数
 */
@Data
public class RequestPageUtil implements Serializable {

    private Integer currentPage = 0;

    private Integer pageSize = 10;

    private Map<String,Object> extraParam;

}
