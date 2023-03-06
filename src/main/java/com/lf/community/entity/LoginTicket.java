package com.lf.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/6 0006 10:56
 * @Version 1.0
 */
@Data
@ToString
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;
    private int status;
    private Date expired;
}
