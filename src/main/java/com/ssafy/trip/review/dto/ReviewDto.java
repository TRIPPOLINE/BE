package com.ssafy.trip.review.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class ReviewDto {
    private int reviewNo;
    private String userId;
    private int spotId;
    private String content;
    private float score;
    private Date tripAt;
}
