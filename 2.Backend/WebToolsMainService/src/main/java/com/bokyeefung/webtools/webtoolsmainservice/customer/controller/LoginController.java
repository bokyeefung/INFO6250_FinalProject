/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.customer.controller;

import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.supplier.service.impl.LoginService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("customerLoginController")
@RequestMapping("/customer/security")
public class LoginController {
    @Autowired
    @Qualifier("customerLoginServiceImpl")
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public UserPo login(@NonNull @RequestBody UserPo userPo) throws ServiceException {
        if (StringUtils.isEmpty(userPo.getName()) || StringUtils.isEmpty(userPo.getPasswd())) {
            log.error("Input param illegal");
            throw new ServiceException("Input param illegal");
        }
        return loginService.login(userPo);
    }

    @PostMapping("/logout")
    public void logout() throws ServiceException {
        loginService.logout();
    }
}
