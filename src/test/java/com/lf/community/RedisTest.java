package com.lf.community;

import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author lf
 * @Create 2023/3/9 0009 10:22
 * @Version 1.0
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void testStrings(){
        String redisKey = "test:count";
        redisTemplate.opsForValue().set(redisKey,1);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
        System.out.println(redisTemplate.opsForValue().increment(redisKey));
        System.out.println(redisTemplate.opsForValue().decrement(redisKey));

    }
    @Test
    public void testHash(){
        String redisKey = "test:user";
        redisTemplate.opsForHash().put(redisKey,"id",1);
        redisTemplate.opsForHash().put(redisKey,"username","zhangsan");
        
        System.out.println(redisTemplate.opsForHash().get(redisKey,"id"));
        System.out.println(redisTemplate.opsForHash().get(redisKey,"username"));
    }

    @Test
    public void testLists(){
        String redisKey = "test:ids";
        redisTemplate.opsForList().leftPush(redisKey,101);
        redisTemplate.opsForList().leftPush(redisKey,102);
        redisTemplate.opsForList().leftPush(redisKey,103);

        System.out.println(redisTemplate.opsForList().size(redisKey));
        System.out.println(redisTemplate.opsForList().index(redisKey,1));
        System.out.println(redisTemplate.opsForList().range(redisKey,0,2));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
//        System.out.println(redisTemplate.opsForList().leftPop(redisKey));
        
    }
    
    @Test
    public void testSets(){
        String redisKey = "test:teachers";
        redisTemplate.opsForSet().add(redisKey,"刘备","关羽","张飞","赵云","诸葛亮");
        System.out.println(redisTemplate.opsForSet().size(redisKey));
        System.out.println(redisTemplate.opsForSet().pop(redisKey));
        System.out.println(redisTemplate.opsForSet().members(redisKey));
    }
    
    @Test
    public void testSortedSets(){
        String redisKey = "test:students";
        
        redisTemplate.opsForZSet().add(redisKey,"aaa",10);
        redisTemplate.opsForZSet().add(redisKey,"eee",30);
        redisTemplate.opsForZSet().add(redisKey,"ddd",40);
        redisTemplate.opsForZSet().add(redisKey,"ccc",90);
        redisTemplate.opsForZSet().add(redisKey,"bbb",20);

        System.out.println(redisTemplate.opsForZSet().zCard(redisKey));
        System.out.println(redisTemplate.opsForZSet().score(redisKey,"ccc"));
        System.out.println(redisTemplate.opsForZSet().reverseRank(redisKey,"ccc"));
        System.out.println(redisTemplate.opsForZSet().reverseRange(redisKey,0,2));
    }
    
    @Test
    public void testKeys(){
        redisTemplate.delete("test:user");
        System.out.println(redisTemplate.hasKey("test:user"));
        redisTemplate.expire("test:students",10, TimeUnit.SECONDS);
    }
    
    @Test
    public void testBoundOpreations(){
        String redisKey ="test:count";
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(redisKey);
        boundValueOperations.increment();
        boundValueOperations.increment();
        boundValueOperations.increment();
        boundValueOperations.increment();
        boundValueOperations.increment();
        System.out.println(boundValueOperations.get());
    }
    
    //编程式事务
    
    @Test
    public void testTransactional(){
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                String redisKey = "test:tx";
                redisOperations.multi();
                redisOperations.opsForSet().add(redisKey,"zhangsan");
                redisOperations.opsForSet().add(redisKey,"lisi");
                redisOperations.opsForSet().add(redisKey,"wangwu");
                System.out.println(redisOperations.opsForSet().members(redisKey));
                return redisOperations.exec();
            }
        });
        System.out.println(obj);

    }
    
}
