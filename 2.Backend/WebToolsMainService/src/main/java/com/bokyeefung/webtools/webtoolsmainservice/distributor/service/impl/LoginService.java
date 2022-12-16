/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.distributor.service.impl;

import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;

public interface LoginService {
    UserPo login(UserPo userPo) throws ServiceException;
    void logout() throws ServiceException;
}
