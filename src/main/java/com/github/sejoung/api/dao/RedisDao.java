package com.github.sejoung.api.dao;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.github.sejoung.api.constant.CommonConstants;

/**
 * @author kim se joung
 *
 */
@Repository("RedisDao")
public class RedisDao {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${redis.expiretime}")
    private int expiretime;

    public void createAuidPcodeData(String key, String data) throws Exception {
        String result = redisTemplate.opsForValue().get(key);

        if (result != null && !"".equals(result)) {
            redisTemplate.opsForValue().set(key, result + CommonConstants.COMMA + data);

        } else {
            redisTemplate.opsForValue().set(key, data);
        }
        redisTemplate.expire(key, expiretime, TimeUnit.DAYS);
    }

    public void createAuidData(String data) throws Exception {
        redisTemplate.opsForSet().add("auid", data);
        redisTemplate.expire("auid", expiretime, TimeUnit.DAYS);
    }
    
    public void createPcodeData(String data) throws Exception {
        redisTemplate.opsForSet().add("pcode", data);
        redisTemplate.expire("pcode", expiretime, TimeUnit.DAYS);
    }
    
    
}
