package com.algebra.generic;

import com.algebra.generic.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author al
 * @date 2019/8/20 9:16
 * @description 泛型测试类-6 ? 与 T
 */
public class GenericTest6 {

    // 区别1：通过 T 来 确保 泛型参数的一致性
    public static  <T extends Animal> Map<String,Object> test(List<T> dst, List<T> src){
        Map<String,Object> result = new HashMap<>();
        // todo: 对象操作
        dst.addAll(src);
        result.put("code","000");
        return result;
    }

    // 区别2：类型参数可以多重限定而通配符不行
    // 使用 & 符号设定多重边界（Multi Bounds)，指定泛型类型 T 必须是 MultiLimitInterfaceA 和 MultiLimitInterfaceB 的共有子类型，
    // 此时变量 t 就具有了所有限定的方法和属性。对于通配符来说，因为它不是一个确定的类型，所以不能进行多重限定。
    public static <T extends MutilInterfaceA & MutilInterfaceB> void test2(T t){

    }

    // 区别3：通配符可以使用超类限定而类型参数不行

    // 类型参数： T extends A

    // 通配符： ? extends A   或者    ? super B


}
