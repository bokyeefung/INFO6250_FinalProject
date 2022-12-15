package com.bokyeefung.webtools.webtoolsmainservice.dao.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class CostPo {
    private String uuid;
    private String relationId;
    private String transType;
    private String isValid;
    private String moneyCost;
    private String timeCost;

}
