/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.webtoolsmainservice.common.cache;

import com.bokyeefung.webtools.cbb.model.constants.UserEntity;
import com.bokyeefung.webtools.cbb.model.dao.entity.UserPo;
import com.bokyeefung.webtools.cbb.util.cache.MemoryCache;
import org.springframework.stereotype.Component;

@Component
public final class UserSecurityCache {
    private static final MemoryCache<UserPo> USER_CACHE = MemoryCache.getInstance(UserPo.class);

    public void updateUser(UserEntity userEntity, UserPo userPo) {
        USER_CACHE.hset(userEntity.name(), userPo);
    }

    public UserPo getUser(UserEntity userEntity) {
        return USER_CACHE.hget(userEntity.name());
    }

    public void removeUser(UserEntity userEntity) {
        updateUser(userEntity, null);
    }
}
