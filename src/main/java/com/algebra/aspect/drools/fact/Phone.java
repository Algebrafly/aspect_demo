package com.algebra.aspect.drools.fact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author al
 * @date 2019/12/30 11:17
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    private String phoneNo;

    private String phoneCustomerName;

    private String phoneCustomerId;

}
