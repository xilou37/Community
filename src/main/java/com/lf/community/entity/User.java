package com.lf.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 18:57
 * @Version 1.0
 */
@Data
@ToString
public class User {
    private int id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private int type;
    private int status;
    private String activationCode;
    private String headerUrl;
    private Date createTime;
}
