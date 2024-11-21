package com.ssafy.trip.spot.dto.response;

import com.ssafy.trip.spot.dto.SpotDto;

import java.util.List;
import lombok.Data;

@Data
public class SearchSpotInBoundResponse {
    private List<SpotDto> spots;
    private Integer nextCursor;

    public SearchSpotInBoundResponse(List<SpotDto> spots, Integer nextCursor){
        this.spots = spots;
        this.nextCursor = nextCursor;
    }
}
