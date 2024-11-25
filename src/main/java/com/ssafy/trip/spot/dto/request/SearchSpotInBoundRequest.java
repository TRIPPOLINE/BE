
package com.ssafy.trip.spot.dto.request;

import lombok.Data;

@Data
public class SearchSpotInBoundRequest {
    private double minLatitude;
    private double maxLatitude;
    private double minLongitude;
    private double maxLongitude;
    //private Integer cursor; // 마지막 ID
    //private int limit;
}