package com.ssafy.trip.recommend.dto.request;

import java.util.List;
import lombok.Data;

@Data
public class TravelRouteRequest {
    private List<String> spots;
}
