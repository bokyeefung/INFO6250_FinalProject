/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao;

import com.bokyeefung.webtools.cbb.model.dao.entity.RelationPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper.RelationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RelationDao extends AbstractDao<RelationPo> {
    public int deleteByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(RelationMapper.class).deleteByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Delete relation by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int insert(RelationPo record) throws ServiceException {
        try {
            return getMapper(RelationMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert relation failed");
            throw new ServiceException(e);
        }
    }

    public int insertSelective(RelationPo record) throws ServiceException {
        try {
            return getMapper(RelationMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert relation failed");
            throw new ServiceException(e);
        }
    }

    public RelationPo selectByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(RelationMapper.class).selectByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Select relation by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int updateByPrimaryKeySelective(RelationPo record) throws ServiceException {
        if (record == null) {
            return 0;
        }
        try {
            return getMapper(RelationMapper.class).updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("Update relation by uuid failed, uuid is: {}", record.getUuid());
            throw new ServiceException(e);
        }
    }

    public RelationPo selectByUuidAndHostGroupId(String uuid, String groupId) throws ServiceException {
        try {
            return getMapper(RelationMapper.class).selectByUuidAndHostGroupId(uuid, groupId);
        } catch (Exception e) {
            log.error("Select relation by uuid failed, uuid is: {}", uuid);
            throw new ServiceException("selectByUuidAndHostGroupId", e);
        }
    }
}
