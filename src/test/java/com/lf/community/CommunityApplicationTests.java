package com.lf.community;

import com.lf.community.entity.User;
import com.lf.community.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {

        User user = new User();
        user.setUsername("xilou");
        user.setEmail("xilou@qq.com");
        user.setSalt("abc");
        user.setType(0);
        user.setStatus(1);
        user.setPassword("test");
        user.setCreateTime(new Date());
        user.setActivationCode("testcode");
        user.setHeaderUrl("http://images.nowcoder.com/head/150t.png");
        int i = userService.insertUser(user);
        System.out.println("执行结果"+i);
    }

}
