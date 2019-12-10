package com.algebra.basic.stream;

import io.swagger.models.auth.In;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author al
 * @date 2019/12/10 9:20
 * @description
 */
public class LambdaTest {

    /**
     * 筛选出符合条件的数据
     * @param objects 数据集合
     * @param predicate 自定义条件
     * @return list
     */
    public static List<StreamTestObject> findEligibility(List<StreamTestObject> objects, Predicate predicate){
        List<StreamTestObject> result = new ArrayList<>();
        objects.forEach(data -> {
            if(predicate.check(data)){
                result.add(data);
            }
        });
        return result;
    }

    /**
     * 返回字符串长度
     * @param s 字符串
     * @param function 函数
     * @return string
     */
    public static int getStringLength(String s, Function<String, Integer> function){
        return function.apply(s);
    }
    public static String getStringDeal(String s, Function<String, String> function){
        return function.apply(s);
    }



    public static void main(String[] args) {

        StreamTestObject o1 = StreamTestObject.builder().name("tom").age(20).salary(new BigDecimal(2000)).build();
        StreamTestObject o2 = StreamTestObject.builder().name("lily").age(21).salary(new BigDecimal(3000)).build();

        List<StreamTestObject> al = new ArrayList<>();
        Collections.addAll(al,o1,o2);

        // 选出年龄大于等于21的人
//        List<StreamTestObject> eligibility = findEligibility(al, new Predicate() {
//            @Override
//            public boolean check(StreamTestObject object) {
//                return object.getAge() >= 21;
//            }
//        });

        // 选出工资大于2000的人
//        List<StreamTestObject> eligibility2 = findEligibility(al,
//                (StreamTestObject object) -> {return object.getSalary().compareTo(new BigDecimal(2000)) > 0;});
        List<StreamTestObject> eligibility2 = findEligibility(al, object -> object.getSalary().compareTo(new BigDecimal(2000)) > 0);

        eligibility2.forEach(System.out::println);

        int stringLength = getStringLength("algebra", String::length);
        System.out.println(stringLength);
        String stringDeal = getStringDeal("algebra_12350aasd", s -> s.substring(0, s.indexOf("_")));
        System.out.println(stringDeal);

    }

}
