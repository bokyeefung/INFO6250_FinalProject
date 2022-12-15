/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.supplier.controller;

import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/supplier/admin")
public class AdminController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/alluser")
    public List<UserPo> selectAllUser() throws ServiceException {
        return Collections.singletonList(userDao.selectByPrimaryKey("1f9fc55d-5937-4db0-80fd-f6e0e05824b3"));
    }
}
