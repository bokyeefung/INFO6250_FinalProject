/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}
