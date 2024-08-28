package cc.realtec.real.bff.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
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
}
