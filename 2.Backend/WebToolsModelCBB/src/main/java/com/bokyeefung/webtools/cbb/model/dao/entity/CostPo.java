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
 * 成本表，存储每种运输关系的运输途径和时间、金钱成本，以及该种运输途径是否有效
 *
 * @since 2022-12-15
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CostPo {
    /**
     * 记录ID
     */
    @JsonProperty("uuid")
    private String uuid;

    /**
     * 关系ID
     */
    @JsonProperty("relationId")
    private String relationId;

    /**
     * 运输途径
     */
    @JsonProperty("transType")
    private String transType;

    /**
     * 是否有效（0-有效，1-无效）
     */
    @JsonProperty("isValid")
    private int isValid;

    /**
     * 运输时间成本
     */
    @JsonProperty("moneyCost")
    private int moneyCost;

    /**
     * 运输金钱成本
     */
    @JsonProperty("timeCost")
    private int timeCost;
}
