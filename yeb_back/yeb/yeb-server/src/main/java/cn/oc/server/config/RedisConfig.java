package cn.oc.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : RedisConfig
 * @Author: oc
 * @Date: 2022/03/05/17:01
 * @Description:    Redis的控制类
 **/
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String ,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //String类型key序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //String类型的value序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //hash类型key序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hash类型的value序列器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return  redisTemplate;
    }
}
