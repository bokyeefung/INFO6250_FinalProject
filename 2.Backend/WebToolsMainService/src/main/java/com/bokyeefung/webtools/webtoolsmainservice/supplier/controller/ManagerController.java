/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.supplier.controller;

import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.supplier.service.impl.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController("supplierManagerController")
@RequestMapping("/supplier/manager")
public class ManagerController {
    @Autowired
    @Qualifier("SupplierManagerServiceImpl")
    private ManagerService managerService;

    public ArticlePo createArticle(ArticlePo articlePo) throws ServiceException {
        return null;
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
        return managerService.queryOrderList();
    }

    public OrderPo confirmOrder(String uuid) throws ServiceException {
        return null;
    }
}
