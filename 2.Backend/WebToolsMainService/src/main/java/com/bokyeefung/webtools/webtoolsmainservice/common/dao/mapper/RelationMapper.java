/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper;

import com.bokyeefung.webtools.cbb.model.dao.entity.RelationPo;
import com.bokyeefung.webtools.cbb.model.dao.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

public interface RelationMapper extends AbstractMapper<RelationPo> {
    RelationPo selectByUuidAndHostGroupId(@Param("uuid") String uuid, @Param("groupId") String groupId);
}