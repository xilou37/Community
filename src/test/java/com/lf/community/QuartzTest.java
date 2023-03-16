package com.lf.community;

import com.lf.community.quartz.AlphaJob;
import org.junit.jupiter.api.Test;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/13 0013 17:48
 * @Version 1.0
 */
@SpringBootTest
public class QuartzTest {
    
    @Autowired
    private Scheduler scheduled;
    
    @Test
    public void testDeleteJob(){
        try {
            boolean result = scheduled.deleteJob(new JobKey("alphaJob", "alphaJobGroup"));
            System.out.println(result);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
