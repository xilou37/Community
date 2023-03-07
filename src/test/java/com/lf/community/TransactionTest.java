package com.lf.community;

import com.lf.community.service.AlphaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/7 0007 17:51
 * @Version 1.0
 */
@SpringBootTest
public class TransactionTest {
    @Autowired
    private AlphaService alphaService;
    
    @Test
    public void testTransaction(){
        Object result = alphaService.save1();
        System.out.println(result);
    }

    @Test
    public void testTransaction2(){
        Object result = alphaService.save2();
        System.out.println(result);
    }
}   
