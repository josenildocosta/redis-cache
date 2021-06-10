package com.example.rediscache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.url}")
    String host;

    @Value("${redis.pass}")
    String pass;

    @Value("${redis.port}")
    String port;

    @Value("${redis.timeout}")
    String timeout;

    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(name = "JedisPoolRules")
    public JedisPool jedisPool() {
        JedisPoolConfig config = jedisPoolConfig();
        if (pass != null && !pass.isEmpty()) {
            return new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), pass);
        } else {
            return new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout));
        }
    }

}
