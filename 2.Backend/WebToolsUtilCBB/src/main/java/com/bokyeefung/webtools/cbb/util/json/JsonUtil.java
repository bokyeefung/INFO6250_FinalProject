/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {
    private static ObjectMapper objectMapper;

    public static <T> String toJson(T obj) {
        initObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        initObjectMapper();
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T, U> Map<T, U> fromJsonMap(String json, Class<T> keyClazz, Class<U> valueClazz) {
        try {
            return objectMapper.readValue(json,
                objectMapper.getTypeFactory().constructMapType(Map.class, keyClazz, valueClazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
    }
}
