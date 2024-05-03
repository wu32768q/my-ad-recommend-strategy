package org.example.bstest.demos.web.proxy;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

public class CaffieneCacheProxy {

    Cache<String, Object> cache = Caffeine.newBuilder()
            .maximumSize(100)
            .build(key -> computeOrGetValue(key)); // 使用lambda表达式作为CacheLoader
}
