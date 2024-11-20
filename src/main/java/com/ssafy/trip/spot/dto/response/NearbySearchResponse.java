package com.ssafy.trip.spot.dto.response;

import com.ssafy.trip.spot.dto.SpotDto;

import java.util.List;
import lombok.Data;
import software.amazon.awssdk.services.s3.endpoints.internal.Value.Int;

@Data
public class NearbySearchResponse {
    private List<SpotDto> spots;
    private Integer nextCursor;

    public NearbySearchResponse(List<SpotDto> spots, Integer nextCursor){
        this.spots = spots;
        this.nextCursor = nextCursor;
    }
}
