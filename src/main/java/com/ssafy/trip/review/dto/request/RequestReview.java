package com.ssafy.trip.review.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
public class RequestReview {
    private String userId;
    private int spotId;
    private String content;
    private float score;
    private Date tripAt;
    private List<MultipartFile> photos; // 사진 파일 리스트
}
