package com.lf.community.controller;

import com.lf.community.entity.DiscussPost;
import com.lf.community.entity.Page;
import com.lf.community.entity.User;
import com.lf.community.service.DiscussPostService;
import com.lf.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 22:40
 * @Version 1.0
 */
@Controller
public class HomeController {
    
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/index",method = RequestMethod.GET)
        public String getIndexPage(Model model, Page page){
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPostsList = new ArrayList<>(); 
        if (discussPosts != null){
            for(DiscussPost discussPost : discussPosts){
                Map<String, Object> map = new HashMap<>();
                map.put("post",discussPost);
                User user = userService.selectById(discussPost.getUserId());
                map.put("user",user);   
                discussPostsList.add(map);
            }
        }
        model.addAttribute("discussPostList",discussPostsList);
        return "index";
        }
    
}
