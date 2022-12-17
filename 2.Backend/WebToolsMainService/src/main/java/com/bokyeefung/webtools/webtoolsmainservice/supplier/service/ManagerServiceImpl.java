/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.supplier.service;

import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.ArticleDao;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.OrderDao;
import com.bokyeefung.webtools.webtoolsmainservice.supplier.service.impl.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("SupplierManagerServiceImpl")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public ArticlePo createArticle(ArticlePo articlePo) throws ServiceException {
        articlePo.setUuid(UUID.randomUUID().toString());
        articleDao.insert(articlePo);
        return articlePo;
    }

    @Override
    public void deleteArticle(String uuid, String groupId) throws ServiceException {
        articleDao.deleteByUuidAndGroupId(uuid, groupId);
    }

    @Override
    public List<ArticlePo> selectAllArticle(String groupId) throws ServiceException {
        return articleDao.selectByGroupId(groupId);
    }

    @Override
    public ArticlePo selectArticleByUuid(String uuid, String groupId) throws ServiceException {
        return articleDao.selectByUuidAndGroupId(uuid, groupId);
    }

    @Override
    public ArticlePo updateArticleCost(Map<String, Object> articlePo) throws ServiceException {
        articleDao.updateCost(articlePo);
        return articleDao.selectByUuidAndGroupId((String) articlePo.get("uuid"), (String) articlePo.get("groupId"));
    }

    @Override
    public List<OrderPo> queryOrderList(String groupId) throws ServiceException {
        return orderDao.selectByGroupId(groupId);
    }

    @Override
    public void confirmOrder(String uuid, String groupId) throws ServiceException {
        orderDao.confirmOrder(uuid, groupId);
    }
}
