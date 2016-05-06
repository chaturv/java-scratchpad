package com.chaturv.redis;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by vineet on 5/4/16.
 */
public class SomeStaticClass {

    public static void register(RedisTemplate redisTemplate) {
        System.out.println("RedisTemplate : " + redisTemplate);

    }
}
