/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.util.cache;

import org.junit.Assert;
import org.junit.Test;

public class MemoryCacheTest {
    @Test
    public void testOnlyOneInstanceForEachClazz() {
        String key = "TEST_KEY";
        String value = "TEST_VALUE";
        MemoryCache<String> stringCache1 = MemoryCache.getInstance(String.class);
        MemoryCache<String> stringCache2 = MemoryCache.getInstance(String.class);
        stringCache1.hset(key, value);
        Assert.assertNotNull(stringCache1);
        Assert.assertEquals(stringCache1, stringCache2);
        Assert.assertEquals(value, stringCache2.hget(key));
    }

    @Test
    public void testDifferentInstanceBetweenClazz() {
        Object stringCache = MemoryCache.getInstance(String.class);
        Object integerCache = MemoryCache.getInstance(Integer.class);
        Assert.assertNotNull(stringCache);
        Assert.assertNotNull(integerCache);
        Assert.assertNotEquals(stringCache, integerCache);
    }
}