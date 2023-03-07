package com.lf.community.service;

import com.lf.community.dao.CommentMapper;
import com.lf.community.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/7 0007 19:31
 * @Version 1.0
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    
    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit){
        return commentMapper.selectCommentByEntity(entityType,entityId,offset,limit);
    }
    public int findCommentCount(int entityType, int entityId){
        return commentMapper.selectCountByEntity(entityType,entityId);
    }
}
