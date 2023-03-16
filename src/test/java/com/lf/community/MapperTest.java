package com.lf.community;

import com.lf.community.dao.DiscussPostMapper;
import com.lf.community.dao.LoginTicketMapper;
import com.lf.community.dao.MessageMapper;
import com.lf.community.entity.DiscussPost;
import com.lf.community.entity.LoginTicket;
import com.lf.community.entity.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Test
    public void test(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0, 10,0);
        for(DiscussPost discussPost : discussPosts){
            System.out.println(discussPost);
        }
        int result = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(result);

    }
    @Test
    public void test1(){

        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setUserId(101);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 *60*60));
        int i = loginTicketMapper.insertLoginTicket(loginTicket);
        System.out.println(i);
        LoginTicket ticket = loginTicketMapper.selectByTicket("abc");
        System.out.println(ticket);
        int abc = loginTicketMapper.updateStatus("abc", 1);
        System.out.println(abc);
    }
    
    @Test
    public void test2(){
        LoginTicket ticket = loginTicketMapper.selectByTicket("abc");
        System.out.println(ticket);
        int abc = loginTicketMapper.updateStatus("abc", 1);
        System.out.println(abc);
    }
    
    @Test
    public void testLetters1(){
        List<Message> messages = messageMapper.selectConversations(111, 0, 20);
        for(Message message : messages){
            System.out.println(message);
        }
        int count = messageMapper.selectConversationCount(111);
        System.out.println(count);
        List<Message> letters = messageMapper.selectLetters("111_112", 0, 10);
        for (Message message : letters){
            System.out.println(message);
        }
        int re = messageMapper.selectLetterCount("111_112");
        System.out.println(re);

        int unreadCo = messageMapper.selectLetterUnreadCount(131, null);
        System.out.println(unreadCo);
        int danCo = messageMapper.selectLetterUnreadCount(131, "111_131");
        System.out.println(danCo);

    }
}
