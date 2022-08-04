package com.url.service.counter;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Profile("prod")
@Service
public final class IncrementingRedisValue implements UniqueValue {

    private final RedisTemplate<String, String> redis;

    public IncrementingRedisValue(RedisTemplate<String, String> redis) {
        this.redis = redis;
    }

    @Override
    public BigInteger next() {
        Long value = redis.opsForValue().increment("shortener.counter");
        return BigInteger.valueOf(value);
    }
}
