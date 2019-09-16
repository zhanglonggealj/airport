package com.esop.airport.task;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.esop.airport.task")
public class TaskConfig implements AsyncConfigurer {

	@Value("${task.core.pool.size}")
	int taskCorePoolSize;

	@Value("${task.max.pool.size}")
	int taskMaxPoolSize;

	@Value("${task.keep.alive.seconds}")
	int taskKeepAliveSeconds;

	@Value("${task.queue.capacity}")
	int taskQueueCapacity;

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(taskCorePoolSize);
		executor.setMaxPoolSize(taskMaxPoolSize);
		executor.setQueueCapacity(taskQueueCapacity);
		executor.setKeepAliveSeconds(taskKeepAliveSeconds);
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new TaskExceptionHandler();
	}

}
