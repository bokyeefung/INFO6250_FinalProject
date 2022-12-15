/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao;
import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ArticleDao extends AbstractDao<ArticlePo> {
    public int deleteByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(ArticleMapper.class).deleteByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Delete article by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int insert(ArticlePo record) throws ServiceException {
        try {
            return getMapper(ArticleMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert article failed");
            throw new ServiceException(e);
        }
    }

    public int insertSelective(ArticlePo record) throws ServiceException {
        try {
            return getMapper(ArticleMapper.class).insert(record);
        } catch (Exception e) {
            log.error("Insert article failed");
            throw new ServiceException(e);
        }
    }

    public ArticlePo selectByPrimaryKey(String uuid) throws ServiceException {
        try {
            return getMapper(ArticleMapper.class).selectByPrimaryKey(uuid);
        } catch (Exception e) {
            log.error("Select article by uuid failed, uuid is: {}", uuid);
            throw new ServiceException(e);
        }
    }

    public int updateByPrimaryKeySelective(ArticlePo record) throws ServiceException {
        if (record == null) {
            return 0;
        }
        try {
            return getMapper(ArticleMapper.class).updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("Update article by uuid failed, uuid is: {}", record.getUuid());
            throw new ServiceException(e);
        }
    }
}