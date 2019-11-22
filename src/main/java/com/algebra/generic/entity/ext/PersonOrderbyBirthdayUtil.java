package com.algebra.generic.entity.ext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * @author al
 * @date 2019/11/22 16:49
 * @description
 */
public class PersonOrderbyBirthdayUtil implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = sdf.parse(o1.getBirthdayString());
            Date date2 = sdf.parse(o2.getBirthdayString());
            return date1.compareTo(date2); // 按照日期从早到晚
//            return date2.compareTo(date1); // 按照日期从晚到早
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
