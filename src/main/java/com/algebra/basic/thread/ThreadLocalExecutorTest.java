package com.algebra.basic.thread;

/**
 * @author al
 * @date 2020/1/13 13:13
 * @description
 */
public class ThreadLocalExecutorTest {

    private static ThreadPoolManager threadPoolManager = ThreadPoolManager.getInstance("");
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("我是主线程1");
        threadPoolManager.startTaskThread(()->{
            System.out.println(threadLocal.get());
        });
        threadLocal.set("我是主线程2");
        threadPoolManager.startTaskThread(()->{
            System.out.println(threadLocal.get());
        });

        //当线程池核心线程数为1的时候2次输出都是 我是主线程1
    }
}
