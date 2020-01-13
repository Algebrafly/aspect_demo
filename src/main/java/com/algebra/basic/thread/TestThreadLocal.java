package com.algebra.basic.thread;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author al
 * @date 2020/1/10 13:29
 * @description
 */
public class TestThreadLocal {

    // 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    /**
     * 获取下一个序列值
     * @return num
     */
    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    private static class TestClient extends Thread {
        private TestThreadLocal sn;
        public TestClient(TestThreadLocal sn){
            this.sn = sn;
        }
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                // 每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + sn.getNextNum() + "]");
            }
        }
    }

    public void simpleTest(){
        //ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("main thread");
        Thread thread = new Thread(()->{
            // 直接使用ThreadLocal输出 null，使用ThreadLocal输出 main thread
            System.out.println(threadLocal.get());
        });
        thread.start();
    }

    public static void main(String[] args) {
        TestThreadLocal sn = new TestThreadLocal();
        // 3个线程共享sn，各自产生序列号
//        TestClient t1 = new TestClient(sn);
//        TestClient t2 = new TestClient(sn);
//        TestClient t3 = new TestClient(sn);
//
//        t1.start();
//        t2.start();
//        t3.start();
//
//        seqNum.remove();

        sn.simpleTest();

    }
}
