/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.supplier.service;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.constants.UserRole;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.UserDao;
import com.bokyeefung.webtools.webtoolsmainservice.supplier.service.impl.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserPo createUser(UserPo userPo) throws ServiceException {
        userPo.setUuid(UUID.randomUUID().toString());
        userPo.setEntity(UserEntity.SUPPLIER);
        userPo.setChangePasswdTime(-1);
        if (UserRole.PLANT_MANAGER.getRoleNumber() == userPo.getRole()) {
            userPo.setGroupId(userPo.getUuid());
        }
        userDao.insert(userPo);
        userDesensitization(userPo);
        return userPo;
    }

    @Override
    public UserPo selectUserByUuid(String uuid) throws ServiceException {
        UserPo userPo = userDao.selectByPrimaryKey(uuid);
        if (userPo == null) {
            log.error("User not fond, uuid is: {}", uuid);
            return null;
        }
        if (!UserEntity.SUPPLIER.equals(userPo.getEntity())) {
            log.error("Permission denied, uuid is: {}", uuid);
            throw new ServiceException("Permission denied");
        }
        userDesensitization(userPo);
        return userPo;
    }

    @Override
    public List<UserPo> selectAllUsers() throws ServiceException {
        return userDao.selectByEntity(UserEntity.SUPPLIER).stream()
            .peek(this::userDesensitization).collect(Collectors.toList());
    }

    @Override
    public void deleteUserByUuid(String uuid) throws ServiceException {
        userDao.deleteByUuidAndEntity(uuid, UserEntity.SUPPLIER);
    }

    private void userDesensitization(UserPo userPo) {
        userPo.setPasswd(null);
    }

}
