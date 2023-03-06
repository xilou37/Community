package com.lf.community.util;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/5 0005 20:13
 * @Version 1.0
 */
public interface CommunityConstant {
    //激活成功
    int ACTIVITION_SUCCESS = 0;
    //重复激活
    int ACTIVITION_REPEAT = 1;
    //激活失败
    int ACTIVITION_FAILURE = 2;
    
    //默认状态的登入凭证的超时时间
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;
    
    //记住状态的登入凭证超时时间
    int REMEMBER_EXPIRED_SECONDS = 3600 *24 * 100;
}
