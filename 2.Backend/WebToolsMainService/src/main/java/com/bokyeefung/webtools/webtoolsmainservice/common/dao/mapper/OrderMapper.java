/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper;

import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.dao.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends AbstractMapper<OrderPo> {
    List<OrderPo> selectByGroupId(@Param("groupId") String groupId);
    OrderPo selectByUuidAndGroupId(@Param("uuid") String uuid, @Param("groupId") String groupId);
    void confirmOrder(@Param("uuid") String uuid, @Param("groupId") String groupId);
}