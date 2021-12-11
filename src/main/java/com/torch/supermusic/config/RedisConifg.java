package com.torch.supermusic.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConifg {
    @Bean
    public RedisTemplate<Object,Object> redisTemplate (RedisConnectionFactory
                                                               redisConnectionFactory)
    {
        RedisTemplate<Object,Object> template= new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jsonRedisSerializer =
                new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om= new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping( LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        //解决redis使用Jackson2JsonRedisSerializer序列化时，反序列化多出类中没有的属性的问题
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        jsonRedisSerializer.setObjectMapper(om);
        template.setDefaultSerializer(jsonRedisSerializer);
        return  template;
    }
}
