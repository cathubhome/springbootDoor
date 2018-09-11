package com.ciwr.config.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步监听(线程任务池)配置
 */
@Configuration
@EnableAsync
@SuppressWarnings("all")
public class ListenerAsyncConfig implements AsyncConfigurer {

    /**
     * 获取异步线程池执行对象
     * @return taskExecutor
     */
    @Override
    public Executor getAsyncExecutor() {
        // 使用Spring内置线程池任务对象
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 下面设置线程池参数
        // 线程池维护线程的最小数量
        taskExecutor.setCorePoolSize(5);
        // 线程池维护线程的最大数量
        taskExecutor.setMaxPoolSize(10);
        // 持有等待执行的任务队列长度
        taskExecutor.setQueueCapacity(25);
        // 线程池维护线程所允许的空闲时间
        taskExecutor.setKeepAliveSeconds(300);
        // 线程池的拒绝策略：ThreadPoolExecutor.AbortPolicy策略（默认）,处理程序遭到拒绝将抛出运行时RejectedExecutionException
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }

}
