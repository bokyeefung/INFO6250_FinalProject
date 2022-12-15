/*
 * Copyright (c) bokyeefung. 2022-2022. All rights reserved.
 */

package com.bokyeefung.webtools.cbb.model.dao.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 *
 * @since 2022-12-15
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserPo {
    /**
     * 用户ID
     */
    @JsonProperty("uuid")
    private String uuid;

    /**
     * 所属经理的ID
     */
    @JsonProperty("groupId")
    private String groupId;

    /**
     * 用户名
     */
    @JsonProperty("name")
    private String name;

    /**
     * 密码
     */
    @JsonProperty("passwd")
    private String passwd;

    /**
     * 下一次强制改密时间
     */
    @JsonProperty("changePasswdTime")
    private long changePasswdTime;

    /**
     * E-mail
     */
    @JsonProperty("email")
    private String email;

    /**
     * 电话号码
     */
    @JsonProperty("phone")
    private String phone;

    /**
     * 地址
     */
    @JsonProperty("address")
    private String address;

    /**
     * 实体类型
     */
    @JsonProperty("entity")
    private String entity;

    /**
     * 角色
     */
    @JsonProperty("role")
    private int role;
}
