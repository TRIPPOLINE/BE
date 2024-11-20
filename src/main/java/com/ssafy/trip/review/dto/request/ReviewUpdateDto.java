package com.ssafy.trip.review.dto.request;

import lombok.Data;

@Data
public class ReviewUpdateDto {
    private int reviewNo;
    private String userId;
    private int spotId;
    private String content;
    private float score;
}
