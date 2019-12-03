package com.algebra.basic.stream;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @projectName: aspect_demo
 * @author: Algebra
 * @description: stream测试类
 * @date: 2019/12/3 20:56
 * @version: 1.0
 */
public class StreamTest {

    private List<StreamTestObject> testArrayList = new ArrayList<>();


    /**
     * 挑选出符合条件的数据
     */
    private void filterTest(){
        List<StreamTestObject> collect = testArrayList.stream().filter(s -> "山东省青岛市".equals(s.getAddress())).collect(Collectors.toList());
//        printResult(collect);
        collect.forEach(System.out::println);
    }

    /**
     * 集合转换：提取对象中某一属性值，单独形成一个该属性值的集合
     */
    private void testMap(){
        //在地址前面加上部分信息，只获取地址输出
        List<String> addresses = testArrayList.stream().map(s ->"住址:"+s.getAddress()).collect(Collectors.toList());
        addresses.forEach(System.out::println);
    }

    /**
     * 简单类型的集合去重
     */
    private static void testDistinctSimple() {
        //简单字符串的去重
        List<String> list = Arrays.asList("111","222","333","111","222");
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 引用类型的集合去重
     */
    private void testDistinctObject() {
        //引用对象的去重，引用对象要实现hashCode和equal方法，否则去重无效
        testArrayList.stream().distinct().forEach(System.out::println);
    }

    /**
     * 简单类型集合排序（默认排序）
     */
    private void testSortSimple() {
        List<String> list = Arrays.asList("333","222","111");
        list.stream().sorted().forEach(System.out::println);
    }

    /**
     * 引用类型集合排序（默认排序）:指定排序规则??
     */
    private void testSortObject() {
        List<StreamTestObject> collect = testArrayList.stream().sorted((o1, o2) -> Long.compare(o1.getId(), o2.getId()))
                .sorted(Comparator.comparingInt(StreamTestObject::getAge)).collect(Collectors.toList());

        printResult(collect);
    }

    /**
     * 集合limit，返回前几个元素
     */
    private void testLimit() {
        List<String> list = Arrays.asList("333","222","111");
        list.stream().limit(2).forEach(System.out::println);
    }

    /**
     * 集合skip，删除前n个元素
     */
    private void testSkip() {
        List<String> list = Arrays.asList("333","222","111");
        list.stream().skip(2).forEach(System.out::println);
    }

    /**
     * 集合reduce,将集合中每个元素聚合成一条数据
     */
    private void testReduce() {
        List<String> list = Arrays.asList("欢","迎","你");
        String appendStr = list.stream().reduce("北京",(a,b) -> a+b);
        System.out.println(appendStr);
    }

    /**
     * 选出集合中年龄最小的人
     */
    private void testMin(){
        StreamTestObject testObject = testArrayList.stream().min(Comparator.comparingInt(StreamTestObject::getAge)).get();
        System.out.println(testObject.toString());
    }

    /**
     * predicate：检测集合中有没有符合某种条件的元素
     */
    private void testMatch(){
        boolean anyMatch = testArrayList.stream().anyMatch(s -> "山东省青岛市".equals(s.getAddress()));
        if(anyMatch){
            System.out.println("有青岛人");
        }

        boolean allMatch = testArrayList.stream().allMatch(s -> s.getAge() >=15);
        if(allMatch){
            System.out.println("所有人都大于15岁");
        }

        boolean noneMatch = testArrayList.stream().noneMatch(s -> "rose".equals(s.getName()));
        if(noneMatch){
            System.out.println("没有叫rose的人");
        }


    }





    public static void main(String[] args) throws ParseException {

        StreamTest test = new StreamTest();
//        test.filterTest();
        test.testMatch();

    }

    private void printResult(List<StreamTestObject> al){
        System.out.println("结果输出为：");
        al.forEach(data->{
            System.out.println(JSONObject.toJSON(data));
        });
    }

    public StreamTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StreamTestObject to1 = StreamTestObject.builder()
                .id((long)1001).name("tom").age(19).address("山东省青岛市").gender(true)
                .birthday(sdf.parse("1994-06-15")).salary(new BigDecimal(1000.50)).build();

        StreamTestObject to2 = StreamTestObject.builder()
                .id((long)1002).name("lily").age(19).address("江苏省南京市").gender(false)
                .birthday(sdf.parse("1997-12-12")).salary(new BigDecimal(2222.50)).build();


        StreamTestObject to3 = StreamTestObject.builder()
                .id((long)1003).name("张小敬").age(22).address("浙江省杭州市").gender(true)
                .birthday(sdf.parse("1988-09-09")).salary(new BigDecimal(3000)).build();


        StreamTestObject to4 = StreamTestObject.builder()
                .id((long)1004).name("檀棋").age(21).address("四川省成都市").gender(false)
                .birthday(sdf.parse("1989-07-07")).salary(new BigDecimal(9999)).build();

        StreamTestObject to5 = StreamTestObject.builder()
                .id((long)1005).name("李逍遥").age(19).address("山东省青岛市").gender(true)
                .birthday(sdf.parse("1995-08-15")).salary(new BigDecimal(1123.50)).build();

        StreamTestObject to6 = StreamTestObject.builder()
                .id((long)1005).name("李逍遥").age(19).address("山东省青岛市").gender(true)
                .birthday(sdf.parse("1995-08-15")).salary(new BigDecimal(1123.50)).build();

        Collections.addAll(testArrayList,to1,to2,to3,to4,to5,to6);
    }

}
