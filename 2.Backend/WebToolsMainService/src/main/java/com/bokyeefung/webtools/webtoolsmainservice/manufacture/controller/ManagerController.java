/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.manufacture.controller;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.cbb.model.exception.UserNotLoginException;
import com.bokyeefung.webtools.cbb.util.check.ParamCheckUtil;
import com.bokyeefung.webtools.webtoolsmainservice.common.cache.UserSecurityCache;
import com.bokyeefung.webtools.webtoolsmainservice.manufacture.service.impl.ManagerService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("manufactureManagerController")
@RequestMapping("/manufacture/manager")
public class ManagerController {
    @Autowired
    private UserSecurityCache userSecurityCache;

    @Autowired
    @Qualifier("manufactureManagerServiceImpl")
    private ManagerService managerService;

    @PostMapping("/orders/host")
    @ResponseBody
    public OrderPo createHostOrder(@NonNull @RequestBody OrderPo orderPo) throws ServiceException {
        ParamCheckUtil.checkParam(orderPo.getRelationId(), "relationId", StringUtils::isNotEmpty);
        ParamCheckUtil.checkParam(orderPo.getNumber(), "number", number -> number > 0);
        UserPo userPo = userSecurityCache.getUser(UserEntity.MANUFACTURE); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.createHostOrder(orderPo, userPo.getGroupId());
    }

    @DeleteMapping("/orders/host/{uuid}")
    @ResponseBody
    public void deleteHostOrder(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        ParamCheckUtil.checkParam(uuid, "uuid", StringUtils::isNotEmpty);
        UserPo userPo = userSecurityCache.getUser(UserEntity.MANUFACTURE); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.deleteHostOrder(uuid, userPo.getGroupId());
    }

    @GetMapping("/orders/host")
    @ResponseBody
    public List<OrderPo> queryHostOrderList() throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.MANUFACTURE); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.queryHostOrderList(userPo.getGroupId());
    }

    @GetMapping("/orders/host/{uuid}")
    @ResponseBody
    public OrderPo queryHostOrder(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.MANUFACTURE); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.queryHostOrder(uuid, userPo.getGroupId());
    }

    @PutMapping("/orders/host")
    @ResponseBody
    public void updateHostOrderNumber(@NonNull @RequestBody OrderPo orderPo) throws ServiceException {
        ParamCheckUtil.checkParam(orderPo.getUuid(), "uuid", StringUtils::isNotEmpty);
        ParamCheckUtil.checkParam(orderPo.getNumber(), "number", number -> number > 0);
        UserPo userPo = userSecurityCache.getUser(UserEntity.MANUFACTURE); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.updateHostOrderNumber(orderPo, userPo.getGroupId());
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrderPo> queryOrderList() throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.MANUFACTURE); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.queryOrderList(userPo.getGroupId());
    }

    @PutMapping("/order/confirm/{uuid}")
    @ResponseBody
    public void confirmOrder(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        ParamCheckUtil.checkParam(uuid, "uuid", StringUtils::isNotEmpty);
        UserPo userPo = userSecurityCache.getUser(UserEntity.MANUFACTURE); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.confirmOrder(uuid, userPo.getGroupId());
    }
}
