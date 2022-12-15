/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.dao;

import com.bokyeefung.webtools.cbb.model.dao.mapper.AbstractMapper;

public class AbstractDao<T extends AbstractMapper<U>, U> {
    protected T getMapper(Class<T> mapperClass) {
        return null;
    }
}
