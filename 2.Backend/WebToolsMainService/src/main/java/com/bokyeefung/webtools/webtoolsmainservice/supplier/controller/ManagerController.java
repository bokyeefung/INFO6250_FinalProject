/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.supplier.controller;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.cbb.model.exception.UserNotLoginException;
import com.bokyeefung.webtools.cbb.util.check.ParamCheckUtil;
import com.bokyeefung.webtools.webtoolsmainservice.common.cache.UserSecurityCache;
import com.bokyeefung.webtools.webtoolsmainservice.supplier.service.impl.ManagerService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Predicate;

@Slf4j
@RestController("supplierManagerController")
@RequestMapping("/supplier/manager")
public class ManagerController {
    @Autowired
    private UserSecurityCache userSecurityCache;

    @Autowired
    @Qualifier("SupplierManagerServiceImpl")
    private ManagerService managerService;

    @PostMapping("/article")
    @ResponseBody
    public ArticlePo createArticle(@NonNull @RequestBody ArticlePo articlePo) throws ServiceException {
        ParamCheckUtil.checkParam(articlePo.getName(), "name", StringUtils::isNotEmpty);
        Predicate<Integer> nonNegativeChecker = number -> number >= 0;
        ParamCheckUtil.checkParam(articlePo.getTimeCost(), "timeCost", nonNegativeChecker);
        ParamCheckUtil.checkParam(articlePo.getMoneyCost(), "moneyCost", nonNegativeChecker);
        ParamCheckUtil.checkParam(articlePo.getNumber(), "number", nonNegativeChecker);
        UserPo userPo = userSecurityCache.getUser(UserEntity.SUPPLIER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        articlePo.setGroupId(userPo.getGroupId());
        return managerService.createArticle(articlePo);
    }

    public void deleteArticle(String uuid) throws ServiceException {

    }

    public List<ArticlePo> selectAllArticle() throws ServiceException {
        return null;
    }

    public ArticlePo selectArticleByUuid() throws ServiceException {
        return null;
    }

    public ArticlePo updateArticleCost() throws ServiceException {
        return null;
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrderPo> queryOrderList() throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.SUPPLIER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.queryOrderList(userPo.getGroupId());
    }

    @PutMapping("/order/confirm/{uuid}")
    @ResponseBody
    public void confirmOrder(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        if (StringUtils.isEmpty(uuid)) {
            throw new ServiceException("Uuid illegal");
        }
        UserPo userPo = userSecurityCache.getUser(UserEntity.SUPPLIER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.confirmOrder(uuid, userPo.getGroupId());
    }
}
