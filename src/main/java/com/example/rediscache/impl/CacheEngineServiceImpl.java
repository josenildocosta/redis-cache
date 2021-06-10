package com.example.rediscache.impl;

import com.example.rediscache.CacheEngineService;
import com.example.rediscache.cache.RedisCachingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CacheEngineServiceImpl implements CacheEngineService {
    private final RedisCachingServiceImpl redisCachingService;

    @Autowired
    public CacheEngineServiceImpl(RedisCachingServiceImpl redisCachingService) {
        this.redisCachingService = redisCachingService;
    }

    public Optional<String> getRegister(String id) {
        return redisCachingService.getCache(id);
    }

    public void setRegister(String id, String register) {
        redisCachingService.setCache(id, register);
    }
}
