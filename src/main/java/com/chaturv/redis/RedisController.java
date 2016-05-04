package com.chaturv.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vineet on 5/4/16.
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //http://localhost:8080/setValue?key=foo:key&value=bar:value
    @RequestMapping("/setValue")
    public String setValue(@RequestParam("key") String key,
                           @RequestParam("value") String value) {
        return set(key, value);

    }

    //http://localhost:8080/addToList?key=foo:list:key&value=bar:value:1
    @RequestMapping("/addToList")
    public List<String> addToList(@RequestParam("key") String key,
                                  @RequestParam("value") String value) {
        return rightPush(key, value);

    }

    private String set(String key, String value) {
        ValueOperations<String, String> valOps = redisTemplate.opsForValue();
        valOps.set(key, value);

        return valOps.get(key);
    }

    private List<String> rightPush(String key, String value) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        listOps.rightPush(key, value);

        return listOps.range(key, 0, -1);

    }
}
