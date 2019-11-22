package com.algebra.generic.entity.ext;

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

        try {
//            df.parse(o1.getSalaryString());
//            df.parse(o2.getBirthdayString());
//            return date1.compareTo(date2); // 按照日期从早到晚
//            return date2.compareTo(date1); // 按照日期从晚到早
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
