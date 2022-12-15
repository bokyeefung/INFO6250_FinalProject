/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.dao;

import com.bokyeefung.webtools.cbb.model.dao.mapper.AbstractMapper;
import com.bokyeefung.webtools.cbb.util.spring.SpringContextHelper;

public abstract class AbstractDao<T> {
    protected <U extends AbstractMapper<T>> U getMapper(Class<U> mapperClass) {
        return SpringContextHelper.getBean(mapperClass);
    }
}
