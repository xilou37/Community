package com.lf.community;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.listener.Topic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/10 0010 13:57
 * @Version 1.0
 */
@SpringBootTest
public class KafkaTests {
    @Autowired
    private KafakaProducer kafakaProducer;
    
    @Test
    public void testKafka(){
        kafakaProducer.sentMessage("test","你好");
        kafakaProducer.sentMessage("test","在吗");
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
@Component
class KafakaProducer{
    
    @Autowired
    private KafkaTemplate kafkaTemplate;
    public void sentMessage(String topic, String content){
        kafkaTemplate.send(topic,content);
    }
}
@Component
class KafakaConsumer00{
    
    @KafkaListener(topics = {"test"})
    public void handleMessage(ConsumerRecord record){
        System.out.println(record.value());
    }
}
