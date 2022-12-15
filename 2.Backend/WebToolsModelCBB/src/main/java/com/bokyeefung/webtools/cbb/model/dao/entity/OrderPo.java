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
 * 订单表，存储各种订单
 *
 * @since 2022-12-15
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderPo {
    /**
     * 订单ID
     */
    @JsonProperty("uuid")
    private String uuid;

    /**
     * 关系ID
     */
    @JsonProperty("relationId")
    private String relationId;

    /**
     * 订购数量
     */
    @JsonProperty("number")
    private int number;

    /**
     * 是否已被确认（0-未确认，1-已确认）
     */
    @JsonProperty("isConfirmed")
    private int isConfirmed;
}
