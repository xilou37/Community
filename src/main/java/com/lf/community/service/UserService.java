package com.lf.community.service;

import com.lf.community.dao.UserMapper;
import com.lf.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 19:08
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    public User selectById(int id){
        return userMapper.selectById(id);
    }
    public int insertUser(User user){
        return userMapper.insertUser(user);
    }
}
