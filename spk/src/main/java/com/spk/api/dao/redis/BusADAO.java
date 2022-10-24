package com.spk.api.dao.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.common.collect.ImmutableMap;
import com.spk.api.entity.redis.BusA;

@Repository
public class BusADAO {

    private static final String BUS_AVAIL_KEY = "BUS:${USER_ID}";

    private final RedisConnectionFactory redisConnectionFactory;

//    private final StringRedisTemplate stringRedisTemplate;

    private final RedisTemplate<String, byte[]> messagePackRedisTemplate;

    private final ObjectMapper messagePackObjectMapper;

    public BusADAO(
        @Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory,
        @Qualifier("stringRedisTemplate") StringRedisTemplate stringRedisTemplate,
        @Qualifier("messagePackRedisTemplate") RedisTemplate<String, byte[]> messagePackRedisTemplate,
        @Qualifier("messagePackObjectMapper") ObjectMapper messagePackObjectMapper
    ) {

        this.redisConnectionFactory = redisConnectionFactory;
//        this.stringRedisTemplate = stringRedisTemplate;
        this.messagePackRedisTemplate = messagePackRedisTemplate;
        this.messagePackObjectMapper = messagePackObjectMapper;
    }

    public BusA getUser(String userid) throws IOException {
        String key = StringSubstitutor.replace(
            BUS_AVAIL_KEY, ImmutableMap.of("USER_ID", userid)
        );

        byte[] message = messagePackRedisTemplate.opsForValue().get(key);

        if (message == null) {
            return null;
        }

        return messagePackObjectMapper.readValue(message, BusA.class);
    }

    public void setUser(BusA busA) throws JsonProcessingException {
        String key = StringSubstitutor.replace(
            BUS_AVAIL_KEY, ImmutableMap.of("USER_ID", busA.getUser_id())
        );

        byte[] message = messagePackObjectMapper.writeValueAsBytes(busA);

        messagePackRedisTemplate.opsForValue().set(key, message, 365, TimeUnit.DAYS);
    }

    public void deleteUser(String userid) {
        String key = StringSubstitutor.replace(
            BUS_AVAIL_KEY, ImmutableMap.of("USER_ID", userid)
        );

        messagePackRedisTemplate.delete(key);
    }

    public List<String> getAllUsers() {
        String key = StringSubstitutor.replace(BUS_AVAIL_KEY, ImmutableMap.of(
            "USER_ID", "*"
        ));

        RedisConnection redisConnection = redisConnectionFactory.getConnection();
        ScanOptions options = ScanOptions.scanOptions().count(50).match(key).build();

        List<String> users = new ArrayList<>();
        Cursor<byte[]> cursor = redisConnection.scan(options);

        while (cursor.hasNext()) {
            String user = StringUtils.replace(new String(cursor.next()), "BUS:", "");

            users.add(user);
        }

        return users;
    }
    
}
