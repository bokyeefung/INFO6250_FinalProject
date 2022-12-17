/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper;

import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.dao.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends AbstractMapper<OrderPo> {
    List<OrderPo> selectByHostGroupId(@Param("groupId") String groupId);
    List<OrderPo> selectByHostUserId(@Param("userId") String userId);
    List<OrderPo> selectByGroupId(@Param("groupId") String groupId);
    OrderPo selectByUuidAndHostGroupId(@Param("uuid") String uuid, @Param("groupId") String groupId);
    OrderPo selectByUuidAndHostUserId(@Param("uuid") String uuid, @Param("userId") String userId);
    OrderPo selectByUuidAndGroupId(@Param("uuid") String uuid, @Param("groupId") String groupId);
    void confirmOrder(@Param("uuid") String uuid, @Param("groupId") String groupId);
    void updateNumberByHostGroupId(@Param("orderPo") OrderPo orderPo, @Param("groupId") String groupId);
    void updateNumberByHostUserId(@Param("orderPo") OrderPo orderPo, @Param("userId") String userId);
}