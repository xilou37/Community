package com.lf.community.controller.interceptor;

import com.lf.community.entity.User;
import com.lf.community.service.DataService;
import com.lf.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/13 0013 10:25
 * @Version 1.0
 */
@Component
public class DataInterceptor implements HandlerInterceptor {
    
    @Autowired
    private DataService dataService;
    
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //统计uv
        String ip = request.getRemoteHost();
        dataService.recordUV(ip);
        //统计dau
        User user = hostHolder.getUser();
        if (user != null){
            dataService.recordDAU(user.getId());
        }
        return true;
    }
}
