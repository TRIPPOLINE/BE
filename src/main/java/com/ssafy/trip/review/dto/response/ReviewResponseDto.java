package com.ssafy.trip.review.dto.response;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReviewResponseDto {
    private int reviewNo;
    private String title;
    private String content;
    private String userName;
    private Timestamp writeAt;
    private Double score;
    private int spotId; //todo:이 스팟의 다른 리뷰 보기 추가 기능
}
