package com.github.sejoung.api.dao;

import java.util.Set;
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
    }
    
    public void createPcodeData2(String data) throws Exception {
        redisTemplate.opsForSet().add("pcode2", data);
    }
    
    public void deletePcodeData2(String key) throws Exception {
        redisTemplate.opsForSet().remove("pcode2", key);
        redisTemplate.delete("pcode:"+key);

    }
    public void createPcodeAuidData(String key,String data) throws Exception {
        redisTemplate.opsForSet().add("pcode:"+key, data);
    }
    
    public Set<String> selectAuidData() throws Exception {
        return redisTemplate.opsForSet().members("auid");
    }
    
    public Set<String> selectPcodeData() throws Exception {
        return redisTemplate.opsForSet().members("pcode");
    }
    
    public Set<String> selectPcodeData2() throws Exception {
        return redisTemplate.opsForSet().members("pcode2");
    }
    
    public Set<String> selectPcodeAuidData(String key) throws Exception {
        return redisTemplate.opsForSet().members("pcode:"+key);
    }
    
    public String selectAuidPcodeDataOne(String key) throws Exception {
        return redisTemplate.opsForValue().get(key);
    }
    
    public void deleteAuidPcodeDataOne(String key) throws Exception {
        redisTemplate.delete("auid:"+key);
        redisTemplate.opsForSet().remove("auid", key);
    }
    
    public void deleteAuidPcodeDataOne2(String key) throws Exception {
        redisTemplate.delete("auid:"+key);
    }
    
    public void updateAuidPcodeData(String key) throws Exception {
        redisTemplate.expire("auid:"+key, expiretime, TimeUnit.DAYS);
    }
}
