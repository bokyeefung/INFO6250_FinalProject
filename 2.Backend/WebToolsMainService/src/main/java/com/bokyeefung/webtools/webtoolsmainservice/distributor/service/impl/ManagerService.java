/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.distributor.service.impl;

import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    ArticlePo createArticle(ArticlePo articlePo) throws ServiceException;
    void deleteArticle(String uuid, String groupId) throws ServiceException;
    List<ArticlePo> selectAllArticle(String groupId) throws ServiceException;
    ArticlePo selectArticleByUuid(String uuid, String groupId) throws ServiceException;
    ArticlePo updateArticleCost(Map<String, Object> articlePo) throws ServiceException;

    OrderPo createHostOrder(OrderPo orderPo, String groupId) throws ServiceException;
    void deleteHostOrder(String uuid, String groupId) throws ServiceException;
    List<OrderPo> queryHostOrderList(String groupId) throws ServiceException;
    OrderPo queryHostOrder(String uuid, String groupId) throws ServiceException;
    void updateHostOrderNumber(OrderPo orderPo, String groupId) throws ServiceException;

    List<OrderPo> queryOrderList(String groupId) throws ServiceException;
    void confirmOrder(String uuid, String groupId) throws ServiceException;
}
