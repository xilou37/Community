package com.lf.community.controller;

import com.lf.community.entity.Comment;
import com.lf.community.entity.DiscussPost;
import com.lf.community.entity.Page;
import com.lf.community.entity.User;
import com.lf.community.service.CommentService;
import com.lf.community.service.DiscussPostService;
import com.lf.community.service.UserService;
import com.lf.community.util.CommunityConstant;
import com.lf.community.util.CommunityUtil;
import com.lf.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/7 0007 11:48
 * @Version 1.0
 */
@Controller
@RequestMapping("discuss")
public class DiscussPostController implements CommunityConstant {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    
    @RequestMapping(path = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content){
        User user = hostHolder.getUser();
        if (user == null){
            return CommunityUtil.getJSONString(403,"你还没有登入哦！");
        }
        DiscussPost post = new DiscussPost();
        post.setTitle(title);
        post.setContent(content);
        post.setUserId(user.getId());
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);
        
        //报错的情况将来统一处理
        return CommunityUtil.getJSONString(0,"发布成功！");
    }
    
    @RequestMapping(path = "/detail/{discussPostId}",method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page){
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post",post);
        //查询帖子对用用户信息
        User user = userService.selectById(post.getUserId());
        model.addAttribute("user",user);
        
        //评论分页信息
        page.setLimit(5);
        page.setPath("/discuss/detail/"+ discussPostId);
        page.setRows(post.getCommentCount());

        //评论：给帖子的评论
        //回复：给评论的评论
        List<Comment> commentList = commentService.findCommentsByEntity(ENTITY_TYPE_POST, post.getId(), page.getOffset(), page.getLimit());
        List<Map<String,Object>> commentVoList = new ArrayList<>();
        if (commentList != null ){
            for (Comment comment : commentList){
                Map<String,Object> commentVo = new HashMap<>();
                commentVo.put("comment",comment);
                commentVo.put("user",userService.selectById(comment.getUserId()));
                //回复列表
                List<Comment> replyList = commentService.findCommentsByEntity(ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                //回复Vo列表
                List<Map<String,Object>> replyVoList = new ArrayList<>();
                if (replyList != null){
                    for (Comment reply : replyList){
                        Map<String,Object> replyVo = new HashMap<>();
                        replyVo.put("reply",reply);
                        replyVo.put("user",userService.selectById(reply.getUserId()));
                        //回复的目标
                        User target = reply.getTargetId() == 0 ? null: userService.selectById(reply.getTargetId());
                        replyVo.put("target",target);
                        
                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replys",replyVoList);
                //回复数量
                int replyCount = commentService.findCommentCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("replyCount",replyCount);
                commentVoList.add(commentVo);
            }
            
        }
        model.addAttribute("comments",commentVoList);

        return "/site/discuss-detail";
    }
    
    
    
}
