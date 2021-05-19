package com.westcatr.emergency.config;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import java.util.concurrent.Executor;

import java.util.concurrent.ThreadPoolExecutor;


@Configuration
@EnableAsync//线程池
public class ThreadFactory {


    private static final Logger logger = LoggerFactory.getLogger(ThreadFactory.class);
     public  static Executor executorPool;



     public static void excutor(Runnable runnable){
         executorPool.execute(runnable);
     }


    @Bean("docExecutor")
    public Executor asyncServiceExecutor() {
        logger.info("开启线程池");
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//配置核心线程数
        executor.setCorePoolSize(5);
//配置最大线程数
        executor.setMaxPoolSize(10);
//配置队列大小
        executor.setQueueCapacity(300);
//配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("docExcutor-");
// rejection-policy：当pool已经达到max size的时候，如何处理新任务
// CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//执行初始化
        executor.initialize();
        this.executorPool =executor;
        return executor;
    }
}
