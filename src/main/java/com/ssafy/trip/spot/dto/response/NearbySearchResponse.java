package com.ssafy.trip.spot.dto.response;

import com.ssafy.trip.spot.dto.SpotDto;

import java.util.List;

public class NearbySearchResponse {
    private List<SpotDto> spots;
    private Long nextCursor;

    public NearbySearchResponse(List<SpotDto> spots, Long nextCursor){
        this.spots = spots;
        this.nextCursor = nextCursor;
    }
}
