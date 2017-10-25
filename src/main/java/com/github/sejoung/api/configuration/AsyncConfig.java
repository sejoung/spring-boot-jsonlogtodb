package com.github.sejoung.api.configuration;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author kim se joung
 *
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor  executor = new ThreadPoolTaskExecutor();
     // 실행 할 pool size를 지정한다. 
        // 기본값 1, 기본적으로 설정한 pool size 대로 thread가 실행되고, 
        // pool size 보다 더 많은 요청이 오는 경우 queue capacity 에 쌓이고 queue capacity에 설정한 값보다 넘는 대기열이 있는 경우에 
        // max pool size에 설정한 값 안에서 thread를 추가로 생성하게 된다.
        // pool size가 처음에는 0 인데 10으로 설정한 경우 core pool size가 10이 초과되면 keepAliveSeconds 시간 이후에 thread를 kill 하는데 
        // core pool size 이하로는 kill을 하지 않는다. pool size 가 10 이상이 된 이후로는 최소 10 pool size를 계속 유지한다.
        executor.setCorePoolSize(100);

        // 최대 pool size 지정, 기본값 Integer.MAX, 
        // 기본적으로는 core pool size 안에서 해결하고 core pool size + queue capacity 를 넘는 요청이 왔을 때만 max pool size 안에서 pool를 생성한다.
        executor.setMaxPoolSize(200);

        // 대기열 size 지정, 기본값 Integer.MAX, 
        // 0으로 설정한 경우 queue type이 SynchronousQueue를, 
        // 0 초과로 설정한 경우 LinkedBlockingQueue 를 생성한다. 
        // 해당 값을 0으로 설정하지 않으면 pool size 가 core pool size 이상 생성되지 않는다 (대기열만 Integer.MAX).
        executor.setQueueCapacity(0);

        // core thread 를 유지할 timeout (초) 지정, 기본값 60(초), setAllowCoreThreadTimeOut 이 true 일 경우에만 작동한다.
        executor.setAwaitTerminationSeconds(60);

        // core thread 를 유휴시간 (keepAliveSeconds)이 지나서 kill 할 지 여부, 
        // 기본값 false, true로 설정한 경우 core pool 도 keepAliveSeconds 시간 이후에 thread 를 kill한다. jdk 1.6 이상부터 사용가능.
        executor.setAllowCoreThreadTimeOut(true);

        // Prefix thread 명 지정
        executor.setThreadNamePrefix("[Async Executor]");

        // queue 대기열 및 task 가 완료된 이후에 shutdown 여부
        executor.setWaitForTasksToCompleteOnShutdown(true);

        // executor 초기화
        executor.initialize();

        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
