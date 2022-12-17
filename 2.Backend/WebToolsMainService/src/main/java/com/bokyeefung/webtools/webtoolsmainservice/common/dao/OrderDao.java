/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao;

import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OrderDao extends AbstractDao<OrderPo> {
    public int deleteByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).deleteByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Delete order by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int insert(OrderPo record) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert order failed");
            throw new ServiceException(e);
        }
    }

    public int insertSelective(OrderPo record) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert order failed");
            throw new ServiceException(e);
        }
    }

    public OrderPo selectByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).selectByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Select order by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int updateByPrimaryKeySelective(OrderPo record) throws ServiceException {
        if (record == null) {
            return 0;
        }
        try {
            return getMapper(OrderMapper.class).updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("Update order by uuid failed, uuid is: {}", record.getUuid());
            throw new ServiceException(e);
        }
    }

    public List<OrderPo> selectByHostGroupId(String groupId) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).selectByHostGroupId(groupId);
        } catch (Exception e) {
            log.error("Select order by host groupId failed, uuid is: {}", groupId);
            throw new ServiceException("Select order by host groupId failed", e);
        }
    }

    public List<OrderPo> selectByGroupId(String groupId) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).selectByGroupId(groupId);
        } catch (Exception e) {
            log.error("Select order by groupId failed, uuid is: {}", groupId);
            throw new ServiceException("Select order by groupId failed", e);
        }
    }

    public OrderPo selectByUuidAndHostGroupId(String uuid, String groupId) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).selectByUuidAndHostGroupId(uuid, groupId);
        } catch (Exception e) {
            log.error("Select order by uuid and groupId failed, uuid is: {}, groupId is: {}", uuid, groupId);
            throw new ServiceException("Select order by groupId failed", e);
        }
    }

    public OrderPo selectByUuidAndGroupId(String uuid, String groupId) throws ServiceException {
        try {
            return getMapper(OrderMapper.class).selectByUuidAndGroupId(uuid, groupId);
        } catch (Exception e) {
            log.error("Select order by uuid and groupId failed, uuid is: {}, groupId is: {}", uuid, groupId);
            throw new ServiceException("Select order by groupId failed", e);
        }
    }

    public void confirmOrder(String uuid, String groupId) throws ServiceException {
        try {
            getMapper(OrderMapper.class).confirmOrder(uuid, groupId);
        } catch (Exception e) {
            log.error("Confirm order by uuid failed, uuid is: {}", uuid);
            throw new ServiceException("Confirm order by uuid failed", e);
        }
    }

    public void updateNumberByHostGroupId(OrderPo orderPo, String groupId) throws ServiceException {
        try {
            getMapper(OrderMapper.class).updateNumberByHostGroupId(orderPo, groupId);
        } catch (Exception e) {
            log.error("Update order by uuid failed, uuid is: {}", orderPo.getUuid());
            throw new ServiceException("Update order by uuid failed", e);
        }
    }
}
