package com.ssafy.trip.review.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewWriteDto {
    private String userId;
    private int spotId;
    private String content;
    private float score;
    private Date tripAt;
}
