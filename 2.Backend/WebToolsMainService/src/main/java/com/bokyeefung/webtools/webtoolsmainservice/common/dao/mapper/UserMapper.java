/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper;

import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.dao.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends AbstractMapper<UserPo> {
    List<UserPo> selectByEntity(String entity);

    int deleteByUuidAndEntity(@Param("uuid") String uuid, @Param("entity") String entity);
}