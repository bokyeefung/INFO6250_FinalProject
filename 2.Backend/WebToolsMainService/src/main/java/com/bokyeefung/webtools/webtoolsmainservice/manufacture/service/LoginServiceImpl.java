/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.manufacture.service;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.UserDao;
import com.bokyeefung.webtools.webtoolsmainservice.supplier.service.impl.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("manufactureLoginServiceImpl")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserPo login(UserPo userPo) throws ServiceException {
        userPo.setEntity(UserEntity.MANUFACTURE);
        UserPo loggedPo = userDao.selectForLogin(userPo);
        if (loggedPo == null) {
            throw new ServiceException("Username or password not true.");
        }
        return loggedPo;
    }

    @Override
    public void logout() throws ServiceException {
        log.info("User logout.");
    }
}
