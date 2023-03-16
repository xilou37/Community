package com.lf.community;

import com.lf.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/13 0013 14:36
 * @Version 1.0
 */
@SpringBootTest
public class ThreadPoolTests {
    
    @Autowired
    private AlphaService alphaService;
    
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTests.class);
    
    //jdk普通线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    //jdk可执行定时任务的线程池
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;
    
    private void sleep(long m){
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //jdk普通线程池测试
    @Test
    public void testExecutorService(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("hello,ExecutorService");
            }
        };
        for (int i=0; i<10; i++){
            executorService.submit(task);
        }
        sleep(10000);
    }
    //jdk定时任务线程池
    @Test
    public void testScheduledExecutorService(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("hello ScheduledExecutorService");
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(task,10000,1000, TimeUnit.MILLISECONDS);
        
        sleep(30000);
    }
    
    //spring 普通线程池
    @Test
    public void testThreadPoolTaskExecutor(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("hello ThreadPoolTaskExecutor");
            }
        };
        for (int i = 0; i < 10; i++) {
            taskExecutor.submit(task);
        }
        sleep(10000);
    }
    //spring 定时任务线程池
    @Test
    public void testThreadPoolTaskSchedule(){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                logger.debug("hello ThreadPoolTaskSchedule");
            }
        };
        Date startTime = new Date(System.currentTimeMillis() + 10000);
        taskScheduler.scheduleAtFixedRate(task,startTime,1000);
        
        sleep(30000);
    }
    
    
    //spring普通线程池（简化）
    @Test
    public void testThreadPoolTaskExecutorSimple(){
        for (int i = 0; i < 10; i++) {
            alphaService.execute1();
        }
        sleep(30000);
    }
    
    //spring定时任务线程池（简化）
    
    @Test
    public void testThreadPoolTaskSchedulerSimple(){
        sleep(30000);
    }
    
    
    
    
    
    
    
    
    
    
}
