/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.model.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserRole {
    ADMIN(1), // 管理员用户
    PLANT_MANAGER(2), // 工厂经理
    MANAGER(3); // 经理

    @Getter
    private final int roleNumber;
}
