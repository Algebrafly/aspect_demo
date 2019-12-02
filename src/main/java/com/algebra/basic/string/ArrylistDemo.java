package com.algebra.basic.string;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author al
 * @date 2019/12/2 13:23
 * @description
 */
public class ArrylistDemo {

    public static void main(String[] args) {

        List<String> al1 = new ArrayList<>();
        al1.add("1");
        al1.add("2");
        al1.add("0");
//        for (String s : al1) {
//            if("1".equals(s)){
//                al1.remove(s);
//            }
//        }

        List<String> al2 = new ArrayList<>();
        al2.add("2");
        al2.add("1");
        al2.add("3");
        al2.add("5");
//        for (String s : al2) {
//            if("1".equals(s)){
//                al2.remove(s);
//            }
//        }

        // 使用迭代器而不使用foreach
        Iterator<String> iterator = al2.iterator();
        while (iterator.hasNext()){
            String item = iterator.next();
            if("2".equals(item)){
                iterator.remove();
            }
        }

        // 使用语法糖简化
        al1.removeIf("1"::equals);


        System.out.println(al1.toString());
        System.out.println(al2.toString());

    }

}
