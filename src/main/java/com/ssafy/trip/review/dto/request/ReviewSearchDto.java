package com.ssafy.trip.review.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewSearchDto {
    private String keyword;
    private String searchType; // "all", "title", "content"
    private int page;
    private int size;
}
