package com.ssafy.trip.review.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewSearchDto {
    private String keyword;
    private int page;
    private int size;
    private String sortBy; // "weight", "latest", "likes"
}
