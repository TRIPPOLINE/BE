package com.ssafy.trip.spot.dto.request;

import lombok.Data;

@Data
public class SpotFilterRequest {
    private Integer areaCode;
    private Integer siGunGuCode;
    private Integer contentTypeId;
}
