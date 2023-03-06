package com.lf.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/6 0006 18:51
 * @Version 1.0
 */
public class CookieUtil {
    public static String getValue(HttpServletRequest request, String name){
        if (request ==null || name ==null){
            throw new IllegalArgumentException("参数为空");
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
