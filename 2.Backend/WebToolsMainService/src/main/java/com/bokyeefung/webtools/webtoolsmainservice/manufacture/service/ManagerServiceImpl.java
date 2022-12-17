/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.manufacture.service;

import com.bokyeefung.webtools.cbb.model.dao.entity.OrderPo;
import com.bokyeefung.webtools.cbb.model.dao.entity.RelationPo;
import com.bokyeefung.webtools.cbb.model.exception.PermissionDeniedException;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.ArticleDao;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.OrderDao;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.RelationDao;
import com.bokyeefung.webtools.webtoolsmainservice.manufacture.service.impl.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service("manufactureManagerServiceImpl")
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RelationDao relationDao;

    @Autowired
    private ArticleDao articleDao;

    @Override
    @Transactional
    public OrderPo createHostOrder(OrderPo orderPo, String groupId) throws ServiceException {
        RelationPo relationPo = relationDao.selectByUuidAndHostGroupId(orderPo.getRelationId(), groupId);
        if (relationPo == null) {
            throw new PermissionDeniedException("relation uuid illegal");
        }
        String uuid = UUID.randomUUID().toString();
        orderPo.setUuid(uuid);
        orderPo.setIsConfirmed(0);
        orderDao.insert(orderPo);
        return orderDao.selectByPrimaryKey(uuid);
    }

    @Override
    public void deleteHostOrder(String uuid, String groupId) throws ServiceException {
        OrderPo orderPo = orderDao.selectByUuidAndHostGroupId(uuid, groupId);
        if (orderPo == null) {
            log.warn("Delete order, order {} not found", uuid);
            return;
        }
        RelationPo relationPo = relationDao.selectByUuidAndHostGroupId(orderPo.getRelationId(), groupId);
        if (relationPo == null) {
            throw new PermissionDeniedException("relation uuid illegal");
        }
        orderDao.deleteByPrimaryKey(uuid);
    }

    @Override
    public List<OrderPo> queryHostOrderList(String groupId) throws ServiceException {
        return orderDao.selectByHostGroupId(groupId);
    }

    @Override
    public OrderPo queryHostOrder(String uuid, String groupId) throws ServiceException {
        return orderDao.selectByUuidAndHostGroupId(uuid, groupId);
    }

    @Override
    public void updateHostOrderNumber(OrderPo orderPo, String groupId) throws ServiceException {
        orderDao.updateNumberByHostGroupId(orderPo, groupId);
    }

    @Override
    public List<OrderPo> queryOrderList(String groupId) throws ServiceException {
        return orderDao.selectByGroupId(groupId);
    }

    @Override
    @Transactional
    public void confirmOrder(String uuid, String groupId) throws ServiceException {
        OrderPo orderPo = orderDao.selectByUuidAndGroupId(uuid, groupId);
        if (orderPo.getNumber() > orderPo.getSrcArticle().getNumber()) {
            throw new ServiceException("Insufficient inventory of raw material " + orderPo.getSrcArticle().getName());
        }
        RelationPo relationPo = relationDao.selectByPrimaryKey(orderPo.getRelationId());
        if (relationPo.getNumber() == 0
            || orderPo.getNumber() / relationPo.getNumber() * relationPo.getNumber() != orderPo.getNumber()) {
            throw new ServiceException("Order number illegal");
        }

        Map<String, Object> srcMap = new HashMap<>();
        srcMap.put("uuid", orderPo.getSrcArticle().getUuid());
        srcMap.put("groupId", orderPo.getSrcArticle().getGroupId());
        srcMap.put("number", orderPo.getSrcArticle().getNumber() - orderPo.getNumber());
        articleDao.updateCost(srcMap);

        Map<String, Object> dstMap = new HashMap<>();
        dstMap.put("uuid", orderPo.getDstArticle().getUuid());
        dstMap.put("groupId", orderPo.getDstArticle().getGroupId());
        dstMap.put("number", orderPo.getDstArticle().getNumber() + (orderPo.getNumber() / relationPo.getNumber()));
        articleDao.updateCost(dstMap);
        orderDao.confirmOrder(uuid, groupId);
    }
}
