package com.ssafy.trip.review.dto.request;

import lombok.Data;

@Data
public class ReviewSearchDto {
    private String keyword;
    private int page;
    private int size;
}
