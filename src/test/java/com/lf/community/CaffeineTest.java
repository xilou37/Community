package com.lf.community;

import com.lf.community.service.DiscussPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/14 0014 16:42
 * @Version 1.0
 */
@SpringBootTest
public class CaffeineTest {
    @Autowired
    private DiscussPostService discussPostService;
    
    @Test
    public void initDataForTest(){
        
    }
}
