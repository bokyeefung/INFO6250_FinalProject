package com.bokyeefung.webtools.webtoolsmainservice.dao.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 物品表，存储原材料、产品及经销商库存信息
 *
 * @since 2022-12-15
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticlePo {
    /**
     * 物品ID
     */
    @JsonProperty("uuid")
    private String uuid;

    /**
     * 所属经理ID
     */
    @JsonProperty("groupId")
    private String groupId;

    /**
     * 物品名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 生产一个单位所需的金钱成本
     */
    @JsonProperty("moneyCost")
    private int moneyCost;

    /**
     * 生产一个单位所需的时间成本
     */
    @JsonProperty("timeCost")
    private int timeCost;

    /**
     * 数量
     */
    @JsonProperty("number")
    private int number;
}
