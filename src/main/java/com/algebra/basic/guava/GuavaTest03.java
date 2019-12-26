package com.algebra.basic.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author al
 * @date 2019/12/26 14:46
 * @description
 */
public class GuavaTest03 {

    /**
     * 基于内存的LoadingCache<K，V>
     */
    public void testLoadingCache(){
        //create a cache for employees based on their employee id
        LoadingCache<String,Employee> employeeCache =
                CacheBuilder.newBuilder()
                        // maximum 100 records can be cached
                        .maximumSize(100)
                        // cache will expire after 30 minutes of access
                        .expireAfterAccess(30, TimeUnit.MINUTES)
                        .build(new CacheLoader<String, Employee>() {
                            // build the cacheLoader
                            @Override
                            public Employee load(String key) throws Exception {
                                //make the expensive call
                                return getFromDatabase(key);
                            }
                        });

        try {
            //on first invocation, cache will be populated with corresponding
            //employee record
            System.out.println("Invocation #1");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));
            //second invocation, data will be returned from cache
            System.out.println("Invocation #2");
            System.out.println(employeeCache.get("100"));
            System.out.println(employeeCache.get("103"));
            System.out.println(employeeCache.get("110"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理字符串加入操作:将字符串按照指定符号连接成整体
     */
    public void testJoiner(){
        String join = Joiner.on(",").skipNulls().join(Arrays.asList(1, 2, 3, 4, 5, null, 6));
        System.out.println(join);
    }

    /**
     * 处理字符串分割操作:将字符串按照指定符号分割，同时除去前后空格，空字符
     */
    public void testSplitter(){
        Iterable<String> split = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog.");
        split.forEach(System.out::println);
    }

    /**
     * 各种JAVA char类型值:匹配、筛选等
     */
    public void testCharMatcher(){
        // only the digits
        System.out.println(CharMatcher.inRange('0', '9').retainFrom("mahesh123"));
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        System.out.println(CharMatcher.whitespace().trimAndCollapseFrom("     Mahesh     Parashar ", ' '));
        // star out all digits
        System.out.println(CharMatcher.inRange('0', '9').replaceFrom("mahesh123", "*"));
        // eliminate all characters that aren't digits or any
        System.out.println(CharMatcher.inRange('0', '9').or(CharMatcher.isNot('h')).retainFrom("mahesh123"));

    }

    /**
     * 一个枚举类工具类：转换大小写和处理下划线、横线
     */
    public void testCaseFormat(){
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }


    private static Employee getFromDatabase(String empId){
        Employee e1 = new Employee("Mahesh", "Finance", "100");
        Employee e2 = new Employee("Rohan", "IT", "103");
        Employee e3 = new Employee("Sohan", "Admin", "110");

        Map<String,Employee> database = new HashMap<>();
        database.put("100", e1);
        database.put("103", e2);
        database.put("110", e3);
        System.out.println("Database hit for " + empId);
        return database.get(empId);
    }

    public static void main(String[] args) {
        GuavaTest03 test03 = new GuavaTest03();
        test03.testCaseFormat();

    }
}
