package com.lf.community.util;

import com.lf.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * Description: 持有用户信息 用于代替session对象
 *
 * @Author lf
 * @Create 2023/3/6 0006 19:04
 * @Version 1.0
 */
@Component
public class HostHolder {
    
    private ThreadLocal<User> users = new ThreadLocal<>();
    
    public void setUser(User user){
        users.set(user);
    }
    public User getUser(){
        return users.get();
    }
    
    public void clear(){
        users.remove();
    }
    
    
}
