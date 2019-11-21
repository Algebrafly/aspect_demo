package com.algebra.aspect.drools.fact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author al
 * @date 2019/11/7 17:21
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String postcode;
    private String street;
    private String state;

    private BigDecimal pyAmt;


    public static void main(String[] args) {

        List<Address> al = new ArrayList<>();

        BigDecimal totalMoney = al.stream().map(Address::getPyAmt).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(String.valueOf(totalMoney.multiply(BigDecimal.valueOf(100)).setScale(0)));

        for (Address address : al) {
            System.out.println(address.getPostcode());
        }



    }


}
