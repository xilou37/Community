package com.lf.community.controller;

import com.alibaba.fastjson.JSONObject;
import com.lf.community.entity.Message;
import com.lf.community.entity.Page;
import com.lf.community.entity.User;
import com.lf.community.service.MessageService;
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
import org.springframework.web.util.HtmlUtils;

import java.util.*;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/8 0008 17:22
 * @Version 1.0
 */
@Controller
public class MessageController implements CommunityConstant {
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private HostHolder hostHolder;
    
    @Autowired
    private UserService userService;
    
    //私信列表
    @RequestMapping(path = "/letter/list",method = RequestMethod.GET)
    public String getLetterList(Model model, Page page){
        User user = hostHolder.getUser();
        page.setLimit(5);
        page.setPath("/letter/list");
        page.setRows(messageService.findConversationCount(user.getId()));
        List<Message> conversationList = messageService.findConversation(user.getId(), page.getOffset(), page.getLimit());
        List<Map<String,Object>> conversations = new ArrayList<>();
        if (conversationList != null){
            for (Message message : conversationList){
                Map<String,Object> map = new HashMap<>();
                map.put("conversation",message);
                map.put("letterCount",messageService.findLetterCount(message.getConversationId()));
                map.put("unreadCount",messageService.findUnreadCount(user.getId(),message.getConversationId()));
                int targetId = user.getId() == message.getFromId() ? message.getToId() : message.getFromId();
                map.put("target",userService.selectById(targetId));
                conversations.add(map);
            }
        }
        model.addAttribute("conversations", conversations);
        //查询当前用户未读消息总数
        int unreadCount = messageService.findUnreadCount(user.getId(), null);
        model.addAttribute("letterUnreadCount", unreadCount);
        int noticeUnreadCount = messageService.findNoticeUnreadCount(user.getId(), null);
        model.addAttribute("noticeUnreadCount",noticeUnreadCount);
        
        return "/site/letter";
    }
    
    @RequestMapping(path = "/letter/detail/{conversationId}",method = RequestMethod.GET)
    public String getLetterDetail(@PathVariable("conversationId") String conversationId, Page page, Model model){
        page.setLimit(5);
        page.setPath("/letter/detail/" + conversationId);
        page.setRows(messageService.findLetterCount(conversationId));
        
        List<Message> letterList = messageService.findLetters(conversationId,page.getOffset(),page.getLimit());
        List<Map<String,Object>> letters = new ArrayList<>();
        if (letterList != null){
            for (Message message : letterList){
                Map<String,Object> map = new HashMap<>();
                map.put("letter",message);
                map.put("fromUser",userService.selectById(message.getFromId()));
                letters.add(map);
            }
        }
        
        model.addAttribute("letters",letters);
        //查询私信的目标
        model.addAttribute("target",getLetterTarget(conversationId));
        //设置为已读
        List<Integer> ids = getLetterIds(letterList);
        if (!ids.isEmpty()){
            messageService.readMessage(ids);
        }
        return "/site/letter-detail"; 

    }
    private User getLetterTarget(String conversationId){
        String[] ids = conversationId.split("_");
        int d0 = Integer.parseInt(ids[0]);
        int d1 = Integer.parseInt(ids[1]);
        if (hostHolder.getUser().getId() == d0){
            return userService.selectById(d1);
        }else {
            return userService.selectById(d0);
        }
    }
    private List<Integer> getLetterIds(List<Message> letterList){
        List<Integer> ids = new ArrayList<>();
        if (letterList != null){
            for (Message message :letterList){
                if (hostHolder.getUser().getId() == message.getToId() && message.getStatus() == 0){
                    ids.add(message.getId());   
                }
            }
        }
        return ids;
    }
    
    @RequestMapping(path = "/letter/send",method = RequestMethod.POST)
    @ResponseBody
    public String sendLetter(String toName, String content){
        User target = userService.findUserByName(toName);
        if (target == null){
            return CommunityUtil.getJSONString(1,"目标用户不存在");
        }
        Message message = new Message();
        message.setFromId(hostHolder.getUser().getId());
        message.setToId(target.getId());
        if (message.getFromId() < message.getToId()){
            message.setConversationId(message.getFromId() + "_" +message.getToId());
        }else {
            message.setConversationId(message.getToId() + "_" +message.getFromId());
        }
        message.setContent(content);
        message.setCreateTime(new Date());
        messageService.addMessage(message);
        
        return CommunityUtil.getJSONString(0);
    }
    
    @RequestMapping(path = "/notice/list",method = RequestMethod.GET)
    public String getNoticeList(Model model){
        User user = hostHolder.getUser();
        //查询评论类的通知
        Message message = messageService.findLatesNotice(user.getId(), TOPIC_COMMENT);
        Map<String,Object> messageVO = new HashMap<>();
        if (message != null){
            messageVO.put("message",message);
            String content = HtmlUtils.htmlUnescape(message.getContent());
            Map<String,Object> data = JSONObject.parseObject(content,HashMap.class);
            
            messageVO.put("user",userService.selectById((Integer) data.get("userId")));
            messageVO.put("entityType",data.get("entityType"));
            messageVO.put("entityId",data.get("entityId"));
            messageVO.put("postId",data.get("postId"));
            
            int count = messageService.findNoticeCount(user.getId(),TOPIC_COMMENT);
            messageVO.put("count",count);
            int unread = messageService.findNoticeUnreadCount(user.getId(), TOPIC_COMMENT);
            messageVO.put("unread",unread);
            
        }else {
            messageVO.put("message",null);
        }
        model.addAttribute("commentNotice",messageVO);
        //查询点赞类的通知
        message = messageService.findLatesNotice(user.getId(), TOPIC_LIKE);
        messageVO = new HashMap<>();
        if (message != null){
            messageVO.put("message",message);
            String content = HtmlUtils.htmlUnescape(message.getContent());
            Map<String,Object> data = JSONObject.parseObject(content,HashMap.class);

            messageVO.put("user",userService.selectById((Integer) data.get("userId")));
            messageVO.put("entityType",data.get("entityType"));
            messageVO.put("entityId",data.get("entityId"));
            messageVO.put("postId",data.get("postId"));

            int count = messageService.findNoticeCount(user.getId(),TOPIC_LIKE);
            messageVO.put("count",count);
            int unread = messageService.findNoticeUnreadCount(user.getId(), TOPIC_LIKE);
            messageVO.put("unread",unread);

        }else {
            messageVO.put("message",null);
        }
        model.addAttribute("likeNotice",messageVO);
        //查询关注类的通知
        message = messageService.findLatesNotice(user.getId(), TOPIC_FOLLOW);
        messageVO = new HashMap<>();
        if (message != null){
            messageVO.put("message",message);
            String content = HtmlUtils.htmlUnescape(message.getContent());
            Map<String,Object> data = JSONObject.parseObject(content,HashMap.class);

            messageVO.put("user",userService.selectById((Integer) data.get("userId")));
            messageVO.put("entityType",data.get("entityType"));
            messageVO.put("entityId",data.get("entityId"));

            int count = messageService.findNoticeCount(user.getId(),TOPIC_FOLLOW);
            messageVO.put("count",count);
            int unread = messageService.findNoticeUnreadCount(user.getId(), TOPIC_FOLLOW);
            messageVO.put("unread",unread);

        }else {
            messageVO.put("message",null);
        }
        model.addAttribute("followNotice",messageVO);
        
        //查询未读消息数量
        int letterUnreadCount = messageService.findUnreadCount(user.getId(), null);
        model.addAttribute("letterUnreadCount",letterUnreadCount);
        int noticeUnreadCount = messageService.findNoticeUnreadCount(user.getId(), null);
        model.addAttribute("noticeUnreadCount",noticeUnreadCount);
        
        return "/site/notice";

    }
    
    @RequestMapping(path = "/notice/detail/{topic}",method = RequestMethod.GET)
    public String getNoticeDetail(@PathVariable("topic") String topic, Page page, Model model){
        User user = hostHolder.getUser();
        
        page.setLimit(5);
        page.setPath("/notice/detail/"+ topic);
        page.setRows(messageService.findNoticeCount(user.getId(),topic));
        
        List<Message> noticeList = messageService.findNotices(user.getId(),topic,page.getOffset(),page.getLimit());
        List<Map<String ,Object>> noticeVoList = new ArrayList<>();
        if (noticeList != null){
            for (Message notice : noticeList){
                Map<String,Object> map = new HashMap<>();
                //通知
                map.put("notice",notice);
                //内容
                String content = HtmlUtils.htmlUnescape(notice.getContent());
                Map<String,Object> data = JSONObject.parseObject(content, HashMap.class);
                map.put("user",userService.selectById((Integer) data.get("userId")));
                map.put("entityType",data.get("entityType"));
                map.put("entityId",data.get("entityId"));
                map.put("postId",data.get("postId"));
                //通知作者
                map.put("fromUser",userService.selectById(notice.getFromId()));
                noticeVoList.add(map);
            }
        }
        model.addAttribute("notices",noticeVoList);
        
        //设置已读
        List<Integer> ids = getLetterIds(noticeList);
        if (!ids.isEmpty()){
            messageService.readMessage(ids);
        }
        
        return "/site/notice-detail";
        
        
        
        
        
        
        
        
    }
    
}
