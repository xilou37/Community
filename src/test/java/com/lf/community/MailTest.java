package com.lf.community;

import com.lf.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/5 0005 15:49
 * @Version 1.0
 */
@SpringBootTest
public class MailTest {
    @Autowired
    private MailClient mailClient;
    
    @Autowired
    private TemplateEngine engine;
    
    @Test
    public void testTextMail(){
        mailClient.sendMail("xilou37@163.com","test","welcome");
    }
    
    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username","xilou");

        String content = engine.process("/mail/demo", context);
        System.out.println(content);
        
        mailClient.sendMail("xilou37@163.com","HTML",content);

    }
}
