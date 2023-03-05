package com.lf.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 21:50
 * @Version 1.0
 */
@Data
@ToString
public class DiscussPost {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int type;
    private int status;
    private Date createTime;
    private int commentCount;
    private int score;
}
