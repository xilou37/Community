package com.lf.community.controller;

import com.lf.community.entity.User;
import com.lf.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 19:10
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
//    @RequestMapping(path = "/user/{id}",method = RequestMethod.GET)
//    public User selecetById(@PathVariable("id") int id){
//        return userService.selectById(id);
//    }
    
//    @RequestMapping(value = "/add",method = RequestMethod.POST)
//    public String addUser(){
//        User user = new User();
//        user.setUsername("xilou");
//        user.setEmail("xilou@qq.com");
//        user.setSalt("abc");
//        user.setType(0);
//        user.setStatus(1);
//        user.setPassword("test");
//        user.setCreateTime(new Date());
//        user.setActivationCode("testcode");
//        user.setHeaderUrl("http://images.nowcoder.com/head/150t.png");
//        int i = userService.insertUser(user);
//        if (i>0){
//            return "success";
//        }else {
//            return "error";
//        }
//    }
}
