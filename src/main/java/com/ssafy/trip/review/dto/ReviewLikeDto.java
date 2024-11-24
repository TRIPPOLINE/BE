package com.ssafy.trip.review.dto;

import lombok.Data;

@Data
public class ReviewLikeDto {
    private int reviewNo;
    private String userId;
    private int likeCount;
    private boolean isLiked;
}
