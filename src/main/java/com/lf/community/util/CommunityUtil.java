package com.lf.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/5 0005 16:50
 * @Version 1.0
 */
public class CommunityUtil {
    
    //生成随机的字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    //MD5 加密 自能加密 不能解密
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
    
    
}
