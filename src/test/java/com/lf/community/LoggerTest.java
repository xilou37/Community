package com.lf.community;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/5 0005 9:55
 * @Version 1.0
 */
@SpringBootTest
public class LoggerTest {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    
    @Test
    public void testLogger(){
        System.out.println(logger.getName());
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warm log");
        logger.error("erroe log");
    }
}
