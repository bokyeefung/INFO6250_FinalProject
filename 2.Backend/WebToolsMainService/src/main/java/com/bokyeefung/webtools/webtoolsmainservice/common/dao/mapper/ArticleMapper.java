/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao.mapper;

import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends AbstractMapper<ArticlePo> {
    List<ArticlePo> selectByGroupId(@Param("groupId") String groupId);
}