package com.ssafy.trip.spot.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SpotSearchRequestDto {
    private Integer areaCode;
    private Integer siGunGuCode;
    private Integer contentTypeId;
    private String keyword;
}
