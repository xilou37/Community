package com.lf.community.controller;

import com.lf.community.entity.DiscussPost;
import com.lf.community.entity.Page;
import com.lf.community.entity.User;
import com.lf.community.service.DiscussPostService;
import com.lf.community.service.LikeService;
import com.lf.community.service.UserService;
import com.lf.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
public class HomeController implements CommunityConstant {
    
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String root(){
        return "forward:/index";
    }
    
    @RequestMapping(value = "/index",method = RequestMethod.GET)
        public String getIndexPage(Model model, Page page,@RequestParam(name="orderMode",defaultValue = "0") int orderMode){
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index?orderMode=" +orderMode);
        List<DiscussPost> discussPosts = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit(),orderMode);
        List<Map<String,Object>> discussPostsList = new ArrayList<>(); 
        if (discussPosts != null){
            for(DiscussPost discussPost : discussPosts){
                Map<String, Object> map = new HashMap<>();
                map.put("post",discussPost);
                User user = userService.selectById(discussPost.getUserId());
                map.put("user",user);

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPost.getId());
                map.put("likeCount",likeCount);
                
                discussPostsList.add(map);
            }
        }
        model.addAttribute("discussPostList",discussPostsList);
        model.addAttribute("orderMode",orderMode);
        return "index";
        }
    
    @RequestMapping(path = "/error",method = RequestMethod.GET)
    public String getErrorPage(){
        return "/error/500";
    }


    //拒绝访问时的提示页面
    @RequestMapping(path = "/denied", method = {RequestMethod.GET})
    public String getDeniedPage() {
        return "/error/404";
    }
}
