package com.lf.community;

import com.lf.community.entity.DiscussPost;
import com.lf.community.service.DiscussPostService;
import org.junit.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/14 0014 17:42
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTest1 {
    
    @Autowired
    private DiscussPostService discussPostService;
    
    private DiscussPost data ;
    
    
    @BeforeClass
    public static void beforeClass(){
        System.out.println("beforeClass");
    }
    @AfterClass
    public static void afterClass(){
        System.out.println("afterClass");
    }
    @Before
    public void before(){
        System.out.println("before");
        //初始化测试数据
        data = new DiscussPost();
        data.setUserId(111);
        data.setTitle("test title");
        data.setContent("test content");
        data.setCreateTime(new Date());
        discussPostService.addDiscussPost(data);
    }
    @After
    public void after(){
        System.out.println("after");
        //删除测试数据
        discussPostService.updateStatus(data.getId(),2);
    }
    
    @Test
    public void test1(){
        System.out.println("test1");
    }
    @Test
    public void test2(){
        System.out.println("test2");
    }
    @Test
    public void testFindById(){
        DiscussPost post = discussPostService.findDiscussPostById(data.getId());
        Assert.assertNotNull(post);
        Assert.assertEquals(data.getTitle(),post.getTitle());
        Assert.assertEquals(data.getContent(),post.getContent());
    }
    @Test
    public void testUpdateScore(){
        int rows = discussPostService.updateScore(data.getId(), 2000);
        Assert.assertEquals(1,rows);
        DiscussPost post = discussPostService.findDiscussPostById(data.getId());
        Assert.assertEquals(2000.00,post.getScore(),2);
    }
    
}
