/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.supplier.service.impl;

import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;

import java.util.List;

public interface ManagerService {
    ArticlePo createArticle(ArticlePo articlePo) throws ServiceException;
    void deleteArticle(String uuid) throws ServiceException;
    List<ArticlePo> selectAllArticle() throws ServiceException;
    ArticlePo selectArticleByUuid() throws ServiceException;
    ArticlePo updateArticleCost() throws ServiceException;

    List<OrderPo> queryOrderList() throws ServiceException;
    OrderPo confirmOrder(String uuid) throws ServiceException;
}