package com.ssafy.trip.review.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
    private String title;
    private String content;
    private Double score;
    private LocalDateTime writeAt;
}
