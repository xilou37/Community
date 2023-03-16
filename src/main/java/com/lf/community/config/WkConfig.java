package com.lf.community.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/14 0014 9:49
 * @Version 1.0
 */
@Configuration
public class WkConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(WkConfig.class);
    
    @Value("${wk.image.storage}")
    private String wkImageStorage;

    @PostConstruct
    public void init(){
        //创建wk 存储的图片路径
        File file = new File(wkImageStorage);
        if (!file.exists()){
            file.mkdir();
            logger.info("已创建wk图片目录" +wkImageStorage);
        }
    }
    
}
