/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.customer.controller;

import com.bokyeefung.webtools.cbb.model.constants.UserRole;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.customer.service.impl.AdminService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("customerAdminController")
@RequestMapping("/customer/admin")
public class AdminController {
    @Autowired
    @Qualifier("customerAdminServiceImpl")
    private AdminService adminService;

    @PostMapping("/user")
    public UserPo createUser(@NonNull @RequestBody UserPo userPo) throws ServiceException {
        // 参数校验
        if (StringUtils.isEmpty(userPo.getName()) || StringUtils.isEmpty(userPo.getPasswd())
            || StringUtils.isEmpty(userPo.getAddress()) || UserRole.ADMIN.getRoleNumber() == userPo.getRole()) {
            throw new ServiceException("userPo is illegal.");
        }
        return adminService.createUser(userPo);
    }

    @PostMapping("/user/{uuid}")
    @ResponseBody
    public UserPo selectUser(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        // 参数校验
        if (StringUtils.isEmpty(uuid)) {
            throw new ServiceException("uuid is illegal.");
        }
        return adminService.selectUserByUuid(uuid);
    }


    @DeleteMapping("/user/{uuid}")
    @ResponseBody
    public void deleteUserByUuid(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        // 参数校验
        if (StringUtils.isEmpty(uuid)) {
            throw new ServiceException("uuid is illegal.");
        }
        adminService.deleteUserByUuid(uuid);
    }
}
