package com.lf.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/7 0007 19:17
 * @Version 1.0
 */
@Data
@ToString
public class Comment {
    
    private int id;
    private int userId;
    private int entityType;
    private int entityId;
    private int targetId;
    private String content;
    private int status;
    private Date createTime;
    
    
}
