package com.lf.community.dao;

import com.lf.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/7 0007 19:20
 * @Version 1.0
 */
@Mapper
public interface CommentMapper {
    List<Comment> selectCommentByEntity(int entityType, int entityId, int offset, int limit);
    
    int selectCountByEntity(int entityType, int entityId);
}
