package com.example.rediscache.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;

@Slf4j
@Service
public class RedisCachingServiceImpl implements RedisCachingService{
    @Qualifier("JedisPoolRules")
    final JedisPool jedisPool;

    @Autowired
    public RedisCachingServiceImpl(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Optional<String> getCache(String key) {
        Jedis jedis = null;

        try {
            jedis = jedisPool.getResource();
            return Optional.ofNullable(jedis.get(key));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	closeConnection(jedis);
        }
        return Optional.empty();
    }

    public void setCache(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(jedis);
        }
    }

	private void closeConnection(Jedis jedis) {
		if (jedis != null) {
            jedis.close();
        }
	}
}
