package com.algebra.basic.string;

/**
 * @author al
 * @date 2019/9/19 15:24
 * @description
 */
public class StringbuilderDemo {

    public static void main(String[] args) throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder(); // 线程不安全
        StringBuffer stringBuffer = new StringBuffer(); // 线程安全
        for (int i = 0; i < 3; i++){
            new Thread(() -> {
                for (int j = 0; j < 1000; j++){
                    stringBuffer.append("a");
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(stringBuffer.length());

    }

}
