/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.customer.service.impl;

import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;

import java.util.List;

public interface AdminService {
    UserPo createUser(UserPo userPo) throws ServiceException;

    UserPo selectUserByUuid(String uuid) throws ServiceException;

    void deleteUserByUuid(String uuid) throws ServiceException;
}
