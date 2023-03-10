package com.lf.community.event;

import com.alibaba.fastjson.JSONObject;
import com.lf.community.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/10 0010 14:25
 * @Version 1.0
 */
@Component
public class EventProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    
    //处理事件
    public void fireEvent(Event event){
        //将事件发布到指定的主题
        kafkaTemplate.send(event.getTopic(), JSONObject.toJSONString(event));
    }
}
