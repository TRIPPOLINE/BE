package com.ssafy.trip.review.dto.request;

import java.sql.Timestamp;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewWriteDto {
    private String userId;
    private int reviewNo;
    private String title;
    private String content;
    private String userName;
    private Double score;
    private int spotId;
}
