package com.algebra.aspect.kafka.order;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author al
 * @date 2019/8/16 15:15
 * @description
 */
@Data
@Builder
public class Order implements Serializable {

    /**
     * 订单id
     */
    private long orderId;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;
}
