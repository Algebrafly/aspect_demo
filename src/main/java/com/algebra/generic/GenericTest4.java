package com.algebra.generic;

import com.algebra.generic.entity.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author al
 * @date 2019/8/20 9:16
 * @description 泛型测试类-4 上界通配符 < ? extends E>
 */
public class GenericTest4 {


    public static  <T extends Animal, E extends Car> Map<String,Object> test(T arg1, E arg2){
        // 限定参数必须是Animal 和 Car 及其子类 (类型参数列表中如果有多个类型参数上限，用逗号分开)
        Map<String,Object> result = new HashMap<>();
        // todo: 对象比较

        result.put("code","000");
        return result;
    }

    public static void main(String[] args) {

        Rabbit r = new Rabbit();
        Dog d = new Dog();
        Chevrolet c = new Chevrolet();

        Map<String, Object> map = test(r, c);


    }

}
