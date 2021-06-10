package com.example.rediscache.cache;

import java.util.Optional;

public interface RedisCachingService {
    Optional<String> getCache(String key);
    void setCache(String key, String value);
}
