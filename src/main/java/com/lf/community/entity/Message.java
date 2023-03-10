package com.lf.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/8 0008 15:54
 * @Version 1.0
 */
@Data
@ToString
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    private int status;
    private Date createTime;
}
