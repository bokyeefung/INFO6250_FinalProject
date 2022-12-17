/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.manufacture.service.impl;

import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;

import java.util.List;

public interface ManagerService {
    List<OrderPo> queryOrderList(String groupId) throws ServiceException;
    void confirmOrder(String uuid, String groupId) throws ServiceException;
}
