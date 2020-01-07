package com.example.demo.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zengweixiong
 * @date 2020/1/7
 */
@Configuration
public class RedisConfig {

    /**
     * redisTemplate 配置修改
     * <p>序列化配置</p>
     *
     * @param lettuceconnectionfactory redis链接
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory lettuceconnectionfactory) {
        StringRedisTemplate template = new StringRedisTemplate(lettuceconnectionfactory);
        // 配置fastjson序列化
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new FastJsonRedisSerializer<>(Object.class));

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new FastJsonRedisSerializer<>(Object.class));

        template.afterPropertiesSet();
        return template;
    }

}
