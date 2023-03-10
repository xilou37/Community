package com.lf.community.dao;

import com.lf.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/8 0008 15:57
 * @Version 1.0
 */
@Mapper
public interface MessageMapper {
    
    //查询当前用户的回话列表，针对每个会话只返回一条最新的私信
    List<Message> selectConversations(int userId, int offset, int limit);
    
    //查询当前用户的会话数量
    int selectConversationCount(int userId);
    
    //查询某个会话所包含的私信列表
    List<Message> selectLetters(String conversationId, int offset, int limit);
    
    //查询某个会话所包含的数量
    int selectLetterCount(String conversationId);
    
    //查询未读私信的数量
    int selectLetterUnreadCount(int userId, String conversationId);
    
    //新增消息
    int insertMessage(Message message);
    
    //修改消息的状态
    int updateStatus(List<Integer> ids, int status);
    
    //通知列表
    //查询某个主题下最新的通知
    Message selectLatesNotice(int userId, String topic);
    //查询某个主题所包含的的通知数量
    int selectNoticeCount(int userId, String topic);
    //查询未读的通知数量
    int selectNoticeUnreadCount(int userId, String topic);
    
    //查询某个主题所包含的通知列表
    List<Message> selectNotices(int userId, String topic, int offset, int limit);
    
    
}
