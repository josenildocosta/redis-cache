package com.example.rediscache;

import java.util.Optional;

public interface CacheEngineService {
    Optional<String> getRegister(String id);
}
