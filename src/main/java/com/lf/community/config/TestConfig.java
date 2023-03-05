package com.lf.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/4 0004 19:44
 * @Version 1.0
 */
@Configuration
public class TestConfig {
    
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
