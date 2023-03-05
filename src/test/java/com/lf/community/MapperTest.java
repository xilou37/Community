package com.lf.community;

import com.lf.community.dao.DiscussPostMapper;
import com.lf.community.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 22:15
 * @Version 1.0
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Test
    public void test(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0, 10);
        for(DiscussPost discussPost : discussPosts){
            System.out.println(discussPost);
        }
        int result = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(result);

    }
}
