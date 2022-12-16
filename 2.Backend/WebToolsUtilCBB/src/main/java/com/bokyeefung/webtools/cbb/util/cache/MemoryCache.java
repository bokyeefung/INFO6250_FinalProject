/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.util.cache;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单实例内存缓存类
 *
 * @param <T> 被缓存对象的类型
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemoryCache<T> {
    private static final Map<Class<?>, MemoryCache<?>> INSTANCE_MAP = new ConcurrentHashMap<>();

    private final Map<String, T> cacheMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> MemoryCache<T> getInstance(Class<T> instanceClazz) {
        if (INSTANCE_MAP.containsKey(instanceClazz)) {
            return (MemoryCache<T>) INSTANCE_MAP.get(instanceClazz);
        }
        MemoryCache<T> memoryCache = new MemoryCache<>();
        INSTANCE_MAP.put(instanceClazz, memoryCache);
        return memoryCache;
    }

    public void hset(String key, T value) {
        if (value == null) {
            cacheMap.remove(key);
            return;
        }
        cacheMap.put(key, value);
    }

    public void hsetAll(Map<String, T> map) {
        cacheMap.putAll(map);
    }

    public T hget(String key) {
        return cacheMap.get(key);
    }

    public Map<String, T> hgetAll() {
        return new HashMap<>(cacheMap);
    }

}
