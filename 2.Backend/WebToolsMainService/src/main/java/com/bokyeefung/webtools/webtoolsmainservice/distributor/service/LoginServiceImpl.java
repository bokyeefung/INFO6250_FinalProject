/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.distributor.service;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.cbb.model.exception.UserNotLoginException;
import com.bokyeefung.webtools.webtoolsmainservice.common.cache.UserSecurityCache;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.UserDao;
import com.bokyeefung.webtools.webtoolsmainservice.distributor.service.impl.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component("distributorLoginServiceImpl")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSecurityCache userSecurityCache;

    @Override
    public UserPo login(UserPo userPo) throws ServiceException {
        userPo.setEntity(UserEntity.DISTRIBUTOR);
        UserPo loggedPo = userDao.selectForLogin(userPo);
        if (loggedPo == null) {
            throw new ServiceException("Username or password not true.");
        }
        userSecurityCache.updateUser(UserEntity.DISTRIBUTOR, loggedPo);
        return loggedPo;
    }

    @Override
    public void logout() throws ServiceException {
        userSecurityCache.removeUser(UserEntity.DISTRIBUTOR);
        log.info("User logout.");

    }

    @Override
    public void checkLogin(String uuid) throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.DISTRIBUTOR); // 获取当前登录用户信息
        if (userPo == null || !StringUtils.equals(userPo.getUuid(), uuid)) {
            throw new UserNotLoginException();
        }
    }
}
