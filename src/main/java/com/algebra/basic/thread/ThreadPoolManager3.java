package com.algebra.basic.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author al
 * @date 2020/1/13 17:12
 * @description
 */
public class ThreadPoolManager3 {
    /**
     * 线程池执行器
     */
    private ThreadPoolExecutor executor = null;

    /*线程池的基础参数 实际使用可写入到配置文件中*/
    /**
     * 核心线程数大小
     */
    private static int SIZE_CORE_POOL = 10;
    /**
     * 最大线程数
     */
    private static int SIZE_MAX_POOL = 15;
    /**
     * 空闲线程等待时间
     */
    private static long keepAliveTime = 0L;
    /**
     * 等待时间单位
     */
    private TimeUnit unit = TimeUnit.MILLISECONDS;
    /**
     * 用来储存等待中的任务的容器
     * 几种选择：
     * ArrayBlockingQueue;
     * LinkedBlockingQueue;
     * SynchronousQueue;
     * 参考：http://blog.csdn.net/mn11201117/article/details/8671497
     */
    private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
    /**
     * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     */
    RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 静态内部类单例模式
     *
     * @return 线程池管理工具
     */
    public static ThreadPoolManager3 getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * 静态内部类，实例化线程池管理工具类
     */
    private static class Inner {
        private static final ThreadPoolManager3 INSTANCE = new ThreadPoolManager3();
    }

    /**
     * 私有构造方法：创建线程池
     */
    private ThreadPoolManager3() {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        executor = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, keepAliveTime,
                unit, workQueue, factory, rejectedExecutionHandler);
        System.out.println("线程池初始化成功！");
    }

    /**
     * 线程池获取方法
     *
     * @return threadPool
     */
    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    /**
     * 准备执行，抛入线程池 （Thread）
     */
    public void execute(Thread t) {
        executor.execute(t);
    }

    /**
     * 准备执行，抛入线程池 （Runnable）
     */
    public void execute(Runnable t) {
        executor.execute(t);
    }

    /**
     * 返回 Future
     * Future.get()可获得返回结果
     *
     * @return Future
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Future<?> submit(Callable t) {
        return getExecutor().submit(t);
    }

    /**
     * 销毁线程池
     */
    public void shutdown() {
        getExecutor().shutdown();
    }


}
