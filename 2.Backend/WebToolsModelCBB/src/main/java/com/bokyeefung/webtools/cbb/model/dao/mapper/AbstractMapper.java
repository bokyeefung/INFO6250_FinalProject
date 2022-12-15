/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.model.dao.mapper;

/**
 * 抽象Dao接口
 *
 * @param <T> 数据库实体类
 * @since 2022-12-15
 */
public interface AbstractMapper<T> {
    int deleteByPrimaryKey(String uuid);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
