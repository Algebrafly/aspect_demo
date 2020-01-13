package com.algebra.basic.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author al
 * @date 2020/1/13 16:26
 * @description
 */
public class ReferenceTest {
    public static void main(String[] args) {
        String s = "test";
        // 软引用：只有在内存不足时候才会被垃圾回收
//        SoftReference<String> reference = new SoftReference<>(s);
        // 当垃圾回收机制触发时候就会将其回收掉
        WeakReference<String> reference = new WeakReference<>(s);
        s = null;
        System.out.println("isEnQueued " + reference.isEnqueued()); // 是否被垃圾回收器标记为即将会回收的状态值。
        if(reference.get() != null){
            System.out.println("s = "+reference.get());
        }
    }
}
