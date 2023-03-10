package com.lf.community.controller.interceptor;

import com.lf.community.entity.User;
import com.lf.community.service.MessageService;
import com.lf.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/10 0010 22:45
 * @Version 1.0
 */
@Component
public class MessageInterceptor implements HandlerInterceptor {
    
    @Autowired
    private HostHolder hostHolder;
    
    @Autowired
    private MessageService messageService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null){
            
            int letterUnreadCount = messageService.findUnreadCount(user.getId(),null);
            int noticeUnreadCount = messageService.findNoticeUnreadCount(user.getId(),null);
            
            modelAndView.addObject("allUnreadCount",letterUnreadCount + noticeUnreadCount);
        }

    }
}
