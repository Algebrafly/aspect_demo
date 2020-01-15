package com.algebra.basic.thread;

import java.util.concurrent.ExecutionException;

/**
 * @author al
 * @date 2020/1/13 17:36
 * @description
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //开十个线程 用来开线程 哈哈
        for (int i = 1; i < 10; i++) {
            MyTask m2 = new MyTask(i);
            m2.start();
        }
    }

    static class MyTask extends Thread {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        /**
         * 拿到线程池
         */
        public ThreadPoolManager3 threadPool = ThreadPoolManager3.getInstance();

        @Override
        public void run() {
            for (int i = 0; i < 150; i++) {
                MyTaskRun myTask = new MyTaskRun(taskNum + "--" + i + "  \n");
                //正题
                threadPool.execute(myTask);
                System.out.println("线程池中线程数目：" + threadPool.getExecutor().getPoolSize() + "，队列中等待执行的任务数目：" +
                        threadPool.getExecutor().getQueue().size() + "，已执行玩别的任务数目：" + threadPool.getExecutor().getCompletedTaskCount());
            }
            System.out.println("ok--- 线程池中线程数目：" + threadPool.getExecutor().getPoolSize() + "，队列中等待执行的任务数目：" +
                    threadPool.getExecutor().getQueue().size() + "，已执行玩别的任务数目：" + threadPool.getExecutor().getCompletedTaskCount());

        }


    }

    static class MyTaskRun implements Runnable {
        private String taskNum;

        public MyTaskRun(String num) {
            this.taskNum = num;
        }

        /**
         * 拿到线程池
         */
        public ThreadPoolManager3 threadPool = ThreadPoolManager3.getInstance();

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(taskNum + "taskNum 线程池中线程数目 ：" + threadPool.getExecutor().getPoolSize() + "，队列中等待执行的任务数目：" +
                    threadPool.getExecutor().getQueue().size() + "，已执行玩别的任务数目：" + threadPool.getExecutor().getCompletedTaskCount());
        }

    }
}
