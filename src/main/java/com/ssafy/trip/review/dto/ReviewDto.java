package com.ssafy.trip.review.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDto {

    private String userId;
    private int spotId;
    private String content;
    private float score;
    private Date writeAt;
    private Date tripAt;
}
