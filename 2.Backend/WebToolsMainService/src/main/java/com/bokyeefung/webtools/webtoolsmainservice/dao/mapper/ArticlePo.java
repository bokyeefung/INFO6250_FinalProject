package com.bokyeefung.webtools.webtoolsmainservice.dao.mapper;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class ArticlePo {
    private String uuid;
    private String groupId;
    private String name;
    private String moneyCost;
    private String timeCost;
    private String number;

}
