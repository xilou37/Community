package com.lf.community;

import com.lf.community.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/7 0007 10:09
 * @Version 1.0
 */
@SpringBootTest
public class SensitiveTest {
    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    public void test(){
        String text = "这里可以赌博，可以吸毒，可以开票，哈哈哈";
        String filter = sensitiveFilter.filter(text);
        System.out.println(filter);
        text = "这里可以♥赌♥博♥，可以♥吸♥毒♥，可以♥开♥票♥，哈哈哈";
        filter = sensitiveFilter.filter(text);
        System.out.println(filter);
    }
}
