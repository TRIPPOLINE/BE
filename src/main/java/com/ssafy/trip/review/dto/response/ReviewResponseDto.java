package com.ssafy.trip.review.dto.response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class ReviewResponseDto {
    private int reviewNo;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime writeAt;
    private BigDecimal score;
    private int spotId;
    private String spotTitle;
    private String spotAddr1;
    private int likeCount;
    private boolean isLiked;
    private List<String> photoUrls;
}
