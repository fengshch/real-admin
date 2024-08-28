package cc.realtec.real.webflux.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
//    @Bean
//    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
//        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext
//                .<String, Object>newSerializationContext(new StringRedisSerializer())
//                .hashValue(new StringRedisSerializer())
//                .hashKey(new StringRedisSerializer())
//                .build();
//
//        return new ReactiveRedisTemplate<>(factory, serializationContext);
//    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate(LettuceConnectionFactory factory) {
        RedisSerializationContext<String, Object> serializationContext = RedisSerializationContext
                .<String, Object>newSerializationContext(RedisSerializer.string())
                .key(RedisSerializer.string())
                .value(RedisSerializer.json())
                .hashValue(RedisSerializer.json())
                .hashKey(RedisSerializer.string())
                .build();

        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        redisTemplate.setKeySerializer(RedisSerializer.string());
//        redisTemplate.setValueSerializer(RedisSerializer.json());
//        redisTemplate.setHashKeySerializer(RedisSerializer.string());
//        redisTemplate.setHashValueSerializer(RedisSerializer.json());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
}
