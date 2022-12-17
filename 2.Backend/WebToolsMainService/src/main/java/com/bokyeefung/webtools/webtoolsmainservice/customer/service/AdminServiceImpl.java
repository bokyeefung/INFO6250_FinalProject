/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.customer.service;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.constants.UserRole;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.UserDao;
import com.bokyeefung.webtools.webtoolsmainservice.customer.service.impl.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component("customerAdminServiceImpl")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserPo createUser(UserPo userPo) throws ServiceException {
        String uuid = UUID.randomUUID().toString();
        userPo.setUuid(uuid);
        userPo.setGroupId(uuid);
        userPo.setEntity(UserEntity.CUSTOMER);
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
        if (!UserEntity.CUSTOMER.equals(userPo.getEntity())) {
            log.error("Permission denied, uuid is: {}", uuid);
            throw new ServiceException("Permission denied");
        }
        userDesensitization(userPo);
        return userPo;
    }

    @Override
    public void deleteUserByUuid(String uuid) throws ServiceException {
        userDao.deleteByUuidAndEntity(uuid, UserEntity.CUSTOMER);
    }

    private void userDesensitization(UserPo userPo) {
        userPo.setPasswd(null);
    }

}
