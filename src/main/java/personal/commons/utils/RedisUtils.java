package personal.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 刘晨
 * @create 2018-04-07 20:49
 * To change this template use File | Settings | Editor | File and Code Templates.
 **/

@Component
public class RedisUtils {

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    private static final long REDIS_DEFAULT_EXPIRE = 600L;

    private static final TimeUnit REDIS_DEFAULT_EXPIRE_TYPE = TimeUnit.SECONDS;

    /**
     * 向redis里面塞值
     * @param key
     * @param value
     */
    public void setRedis(String key,Object value){
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key,REDIS_DEFAULT_EXPIRE,REDIS_DEFAULT_EXPIRE_TYPE);
    }

    public void setRedis(String key,Object value,long expireTime,TimeUnit expireType){
        redisTemplate.opsForValue().set(key,value);
        if(expireTime > 0){
            redisTemplate.expire(key,expireTime,expireType);
        }
    }

    public Object getRedisValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public Object getRedisValue7ResetTime(String key){
        Object value = redisTemplate.opsForValue().get(key);
        if(value != null){
            redisTemplate.expire(key,REDIS_DEFAULT_EXPIRE,REDIS_DEFAULT_EXPIRE_TYPE);
        }
        return value;
    }
}
