/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.manufacture.service;

import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.ArticleDao;
import com.bokyeefung.webtools.webtoolsmainservice.manufacture.service.impl.PlantManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("manufacturePlantManagerServiceImpl")
public class PlantManagerServiceImpl implements PlantManagerService {

    @Autowired
    private ArticleDao articleDao;

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
}
