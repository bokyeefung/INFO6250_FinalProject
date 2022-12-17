/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.customer.controller;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.cbb.model.exception.UserNotLoginException;
import com.bokyeefung.webtools.cbb.util.check.ParamCheckUtil;
import com.bokyeefung.webtools.webtoolsmainservice.common.cache.UserSecurityCache;
import com.bokyeefung.webtools.webtoolsmainservice.customer.service.impl.ManagerService;
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
import java.util.Map;
import java.util.function.Predicate;

@Slf4j
@RestController("customerManagerController")
@RequestMapping("/customer/manager")
public class ManagerController {
    public static final String UUID_KEY = "uuid";
    public static final String MONEY_COST_KEY = "moneyCost";
    public static final String TIME_COST_KEY = "timeCost";
    public static final String NUMBER_KEY = "number";

    @Autowired
    private UserSecurityCache userSecurityCache;

    @Autowired
    @Qualifier("customerManagerServiceImpl")
    private ManagerService managerService;

    @PostMapping("/article")
    @ResponseBody
    public ArticlePo createArticle(@NonNull @RequestBody ArticlePo articlePo) throws ServiceException {
        ParamCheckUtil.checkParam(articlePo.getName(), "name", StringUtils::isNotEmpty);
        Predicate<Integer> nonNegativeChecker = number -> number >= 0;
        ParamCheckUtil.checkParam(articlePo.getTimeCost(), "timeCost", nonNegativeChecker);
        ParamCheckUtil.checkParam(articlePo.getMoneyCost(), "moneyCost", nonNegativeChecker);
        ParamCheckUtil.checkParam(articlePo.getNumber(), "number", nonNegativeChecker);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        articlePo.setGroupId(userPo.getGroupId());
        return managerService.createArticle(articlePo);
    }

    @DeleteMapping("/article/{uuid}")
    public void deleteArticle(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        ParamCheckUtil.checkParam(uuid, "uuid", StringUtils::isNotEmpty);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.deleteArticle(uuid, userPo.getGroupId());
    }

    @GetMapping("/article")
    @ResponseBody
    public List<ArticlePo> selectAllArticle() throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.selectAllArticle(userPo.getGroupId());
    }

    @GetMapping("/article/{uuid}")
    @ResponseBody
    public ArticlePo selectArticleByUuid(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        ParamCheckUtil.checkParam(uuid, "uuid", StringUtils::isNotEmpty);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.selectArticleByUuid(uuid, userPo.getGroupId());
    }

    @PutMapping("/article")
    @ResponseBody
    public ArticlePo updateArticle(@NonNull @RequestBody Map<String, Object> articlePo) throws ServiceException {
        ParamCheckUtil.checkParam(articlePo.get(UUID_KEY), UUID_KEY, uuid -> StringUtils.isNotEmpty((String) uuid));
        Predicate<Object> nonNegativeChecker = number -> number != null && (Integer) number >= 0;
        if (articlePo.containsKey(MONEY_COST_KEY)) {
            ParamCheckUtil.checkParam(articlePo.get(MONEY_COST_KEY), MONEY_COST_KEY, nonNegativeChecker);
        }
        if (articlePo.containsKey(TIME_COST_KEY)) {
            ParamCheckUtil.checkParam(articlePo.get(TIME_COST_KEY), TIME_COST_KEY, nonNegativeChecker);
        }
        if (articlePo.containsKey(NUMBER_KEY)) {
            ParamCheckUtil.checkParam(articlePo.get(NUMBER_KEY), NUMBER_KEY, nonNegativeChecker);
        }
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        articlePo.put("groupId", userPo.getGroupId());
        return managerService.updateArticleCost(articlePo);
    }

    @PostMapping("/orders/host")
    @ResponseBody
    public OrderPo createHostOrder(@NonNull @RequestBody OrderPo orderPo) throws ServiceException {
        ParamCheckUtil.checkParam(orderPo.getRelationId(), "relationId", StringUtils::isNotEmpty);
        ParamCheckUtil.checkParam(orderPo.getNumber(), "number", number -> number > 0);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        orderPo.setUserId(userPo.getUuid());
        return managerService.createHostOrder(orderPo, userPo.getGroupId());
    }

    @DeleteMapping("/orders/host/{uuid}")
    @ResponseBody
    public void deleteHostOrder(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        ParamCheckUtil.checkParam(uuid, "uuid", StringUtils::isNotEmpty);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.deleteHostOrder(uuid, userPo.getUuid());
    }

    @GetMapping("/orders/host")
    @ResponseBody
    public List<OrderPo> queryHostOrderList() throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.queryHostOrderList(userPo.getUuid());
    }

    @GetMapping("/orders/host/{uuid}")
    @ResponseBody
    public OrderPo queryHostOrder(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.queryHostOrder(uuid, userPo.getUuid());
    }

    @PutMapping("/orders/host")
    @ResponseBody
    public void updateHostOrderNumber(@NonNull @RequestBody OrderPo orderPo) throws ServiceException {
        ParamCheckUtil.checkParam(orderPo.getUuid(), "uuid", StringUtils::isNotEmpty);
        ParamCheckUtil.checkParam(orderPo.getNumber(), "number", number -> number > 0);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.updateHostOrderNumber(orderPo, userPo.getUuid());
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrderPo> queryOrderList() throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        return managerService.queryOrderList(userPo.getGroupId());
    }

    @PutMapping("/order/confirm/{uuid}")
    @ResponseBody
    public void confirmOrder(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        ParamCheckUtil.checkParam(uuid, "uuid", StringUtils::isNotEmpty);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        managerService.confirmOrder(uuid, userPo.getGroupId());
    }
}
