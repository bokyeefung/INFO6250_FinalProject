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
 * 关系邻接表，存储产品和原材料、库存和产品以及用户和经销商的关系
 *
 * @since 2022-12-15
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelationPo {
    /**
     * 关系的ID
     */
    @JsonProperty("uuid")
    private String uuid;

    /**
     * 生产者ID
     */
    @JsonProperty("sourceId")
    private String sourceId;

    /**
     * 消费者ID
     */
    @JsonProperty("destinationId")
    private String destinationId;
}