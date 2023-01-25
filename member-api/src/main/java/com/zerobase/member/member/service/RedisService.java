package com.zerobase.member.member.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.member.exception.CustomException;
import com.zerobase.member.exception.ErrorCode;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public <T> T getRedis(String key, Class<T> classType) {
        if (StringUtil.isNullOrEmpty(key)) {
            return null;
        }

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String redisValue = (String) ops.get(key);

        if (StringUtil.isNullOrEmpty(redisValue)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(redisValue, classType);
            } catch (JsonProcessingException ex) {
                return null;
            }
        }
    }

    public void putRedis(String key, Object classType) {
        if (StringUtil.isNullOrEmpty(key)) {
            throw new CustomException(ErrorCode.REDIS_PUT_EMPTY_KEY);
        }

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        try {
            ops.set(key, objectMapper.writeValueAsString(classType));
        } catch (JsonProcessingException ex) {
            throw new CustomException(ErrorCode.REDIS_PUT_FAIL);
        }
    }

    public void putRedis(String key, Object classType, TimeUnit expireTimeUnit, Long expireTime) {
        if (StringUtil.isNullOrEmpty(key)) {
            throw new CustomException(ErrorCode.REDIS_PUT_EMPTY_KEY);
        }

        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        try {
            ops.set(key, objectMapper.writeValueAsString(classType));
            redisTemplate.expire(key, expireTime, expireTimeUnit);
        } catch (JsonProcessingException ex) {
            throw new CustomException(ErrorCode.REDIS_PUT_FAIL);
        }
    }

    public void delRedis(String key) {
        if (!StringUtil.isNullOrEmpty(key)) {
            redisTemplate.delete(key);
        }
    }
}