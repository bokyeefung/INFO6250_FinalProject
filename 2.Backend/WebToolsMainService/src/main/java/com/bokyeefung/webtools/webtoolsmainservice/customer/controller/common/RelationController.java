/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.customer.controller.common;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.dao.entity.ArticlePo;
import com.bokyeefung.webtools.cbb.model.dao.entity.RelationPo;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.model.exception.ServiceException;
import com.bokyeefung.webtools.cbb.model.exception.UserNotLoginException;
import com.bokyeefung.webtools.cbb.util.check.ParamCheckUtil;
import com.bokyeefung.webtools.cbb.util.json.JsonUtil;
import com.bokyeefung.webtools.webtoolsmainservice.common.cache.UserSecurityCache;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.ArticleDao;
import com.bokyeefung.webtools.webtoolsmainservice.common.dao.RelationDao;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("customerRelationController")
@RequestMapping("/customer/common/relation")
public class RelationController {
    @Autowired
    private UserSecurityCache userSecurityCache;

    @Autowired
    private RelationDao relationDao;

    @Autowired
    private ArticleDao articleDao;

    @GetMapping("/{uuid}")
    @ResponseBody
    public Map<String, Object> queryRelation(@NonNull @PathVariable("uuid") String uuid) throws ServiceException {
        ParamCheckUtil.checkParam(uuid, "uuid", StringUtils::isNotEmpty);
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        RelationPo relationPo = relationDao.selectByUuidAndHostGroupId(uuid, userPo.getGroupId());
        return convertToDto(relationPo, userPo.getGroupId());
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Map<String, Object>> queryAllRelations() throws ServiceException {
        UserPo userPo = userSecurityCache.getUser(UserEntity.CUSTOMER); // 获取当前登录用户信息
        if (userPo == null) {
            throw new UserNotLoginException();
        }
        List<RelationPo> relationPoList = relationDao.selectByHostGroupId(userPo.getGroupId());
        List<Map<String, Object>> solutionList = new ArrayList<>(relationPoList.size());
        for (RelationPo relationPo : relationPoList) {
            solutionList.add(convertToDto(relationPo, userPo.getGroupId()));
        }
        return solutionList;
    }

    private Map<String, Object> convertToDto(RelationPo relationPo, String groupId) throws ServiceException {
        ArticlePo srcArticlePo = articleDao.selectByPrimaryKey(relationPo.getSourceId());
        ArticlePo dstArticlePo = articleDao.selectByUuidAndGroupId(relationPo.getDestinationId(), groupId);
        Map<String, Object> solution = JsonUtil.fromJsonMap(JsonUtil.toJson(relationPo), String.class, Object.class);
        solution.put("srcArticle", srcArticlePo);
        solution.put("dstArticle", dstArticlePo);
        return solution;
    }
}
