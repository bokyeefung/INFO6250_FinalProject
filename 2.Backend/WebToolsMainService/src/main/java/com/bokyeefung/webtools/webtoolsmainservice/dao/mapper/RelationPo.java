package com.bokyeefung.webtools.webtoolsmainservice.dao.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RelationPo {
    private String uuid;
    private String sourceId;
    private String destinationId;

}