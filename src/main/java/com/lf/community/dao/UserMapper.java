package com.lf.community.dao;

import com.lf.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 19:04
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    
    User selectById(int id);
    User selectByName(String username);
    User selectByEmail(String email);

    int updateStatus(int id, int status);
    int updateHeader(int id, String headerUrl);
    int updatePassword(int id, String password);
    
    int insertUser(User user);
    
    
}
