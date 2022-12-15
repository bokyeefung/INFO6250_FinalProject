/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao;

import com.bokyeefung.webtools.cbb.model.dao.entity.CostPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper.CostMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CostDao extends AbstractDao<CostPo> {
    public int deleteByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(CostMapper.class).deleteByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Delete cost by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int insert(CostPo record) throws ServiceException {
        try {
            return getMapper(CostMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert cost failed");
            throw new ServiceException(e);
        }
    }

    public int insertSelective(CostPo record) throws ServiceException {
        try {
            return getMapper(CostMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert cost failed");
            throw new ServiceException(e);
        }
    }

    public CostPo selectByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(CostMapper.class).selectByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Select cost by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int updateByPrimaryKeySelective(CostPo record) throws ServiceException {
        if (record == null) {
            return 0;
        }
        try {
            return getMapper(CostMapper.class).updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("Update cost by uuid failed, uuid is: {}", record.getUuid());
            throw new ServiceException(e);
        }
    }
}
