package com.algebra.generic.entity.ext;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * @author al
 * @date 2019/11/22 16:49
 * @description
 */
public class PersonOrderbySalaryUtil implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        DecimalFormat df = new DecimalFormat("0.00");

        BigDecimal data1 = o1.getSalary();
        BigDecimal data2 = o2.getSalary();
        return data1.compareTo(data2); // 按照日期从早到晚
//            return date2.compareTo(date1); // 按照日期从晚到早
    }
}
