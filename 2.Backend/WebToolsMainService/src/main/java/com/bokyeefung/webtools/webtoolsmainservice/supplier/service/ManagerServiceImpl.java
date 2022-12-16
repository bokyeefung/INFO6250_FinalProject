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

@Service("SupplierManagerServiceImpl")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public ArticlePo createArticle(ArticlePo articlePo) throws ServiceException {
        return null;
    }

    @Override
    public void deleteArticle(String uuid) throws ServiceException {

    }

    @Override
    public List<ArticlePo> selectAllArticle() throws ServiceException {
        return null;
    }

    @Override
    public ArticlePo selectArticleByUuid() throws ServiceException {
        return null;
    }

    @Override
    public ArticlePo updateArticleCost() throws ServiceException {
        return null;
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
