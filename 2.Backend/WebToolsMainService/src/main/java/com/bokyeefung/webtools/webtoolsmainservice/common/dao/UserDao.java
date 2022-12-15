/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao;

import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDao extends AbstractDao<UserPo> {
    public int deleteByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(UserMapper.class).deleteByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Delete user by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int insert(UserPo record) throws ServiceException {
        try {
            return getMapper(UserMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert user failed");
            throw new ServiceException(e);
        }
    }

    public int insertSelective(UserPo record) throws ServiceException {
        try {
            return getMapper(UserMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert user failed");
            throw new ServiceException(e);
        }
    }

    public UserPo selectByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(UserMapper.class).selectByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Select user by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int updateByPrimaryKeySelective(UserPo record) throws ServiceException {
        if (record == null) {
            return 0;
        }
        try {
            return getMapper(UserMapper.class).updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("Update user by uuid failed, uuid is: {}", record.getUuid());
            throw new ServiceException(e);
        }
    }
}
