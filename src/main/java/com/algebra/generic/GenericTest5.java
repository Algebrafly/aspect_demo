package com.algebra.generic;

import com.algebra.generic.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2019/8/20 9:16
 * @description 泛型测试类-5 下界通配符 < ? super E>
 */
public class GenericTest5 {


    public static  <T> Map<String,Object> test(List<? super T> dst, List<T> src){
        // 表示参数化的类型可能是所指定的类型，或者是此类型的父类型，直至 Object (类型参数列表中如果有多个类型参数上限，用逗号分开)
        Map<String,Object> result = new HashMap<>();
        // todo: 对象操作
        dst.addAll(src);

        result.put("code","000");
        return result;
    }

    public static void main(String[] args) {

        // dst 类型 “大于等于” src 的类型，这里的“大于等于”是指 dst 表示的范围比 src 要大，因此装得下 dst 的容器也就能装 src 。
        List<Dog> dogs = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();

        Map<String, Object> map = test(animals, dogs);


    }

}
