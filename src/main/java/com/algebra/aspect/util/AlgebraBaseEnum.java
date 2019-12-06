package com.algebra.aspect.util;

import com.algebra.aspect.mapstruct.domain.Person;
import com.algebra.aspect.mapstruct.domain.User;
import lombok.Data;

import java.util.Date;

/**
 * @author al
 * @date 2019/12/6 13:47
 * @description
 */
public enum AlgebraBaseEnum {


    EXAMPLE_1("key-1", 123),
    EXAMPLE_2("key-2", "asd"),
    EXAMPLE_3("key-3", new Person(123L, "tom", "", new Date(), new User())),

    EXAMPLE_4("key-4", new Person(321L, "lily", "", new Date(), new User())) {
        @Override
        public User getObject(Person person) {
            return person.getUser();
        }
    },
    EXAMPLE_5(){}
    ;

    // Handy constants for conversion methods
    static final long C0 = 1L;
    static final long C1 = C0 * 1000L;
    static final long C2 = C1 * 1000L;
    static final long C3 = C2 * 1000L;
    static final long C4 = C3 * 60L;
    static final long C5 = C4 * 60L;
    static final long C6 = C5 * 24L;

    static final long MAX = Long.MAX_VALUE;

    /**
     * Scale d by m, checking for overflow.
     * This has a short name to make above code more readable.
     */
    static long x(long d, long m, long over) {
        if (d > over) {
            return Long.MAX_VALUE;
        }
        if (d < -over) {
            return Long.MIN_VALUE;
        }
        return d * m;
    }

    public User getObject(Person person) {
        throw new AbstractMethodError();
    }

    /**
     * 根据枚举的key值来搜索value值
     *
     * @param key key
     * @return value
     */
    public Object getValueByKey(String key) {
        for (AlgebraBaseEnum v : AlgebraBaseEnum.values()) {
            if (v.getKey().endsWith(key)) {
                return v.getValue();
            }
        }
        return null;
    }

    AlgebraBaseEnum() {
    }

    AlgebraBaseEnum(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    private String key;
    private Object value;

}
