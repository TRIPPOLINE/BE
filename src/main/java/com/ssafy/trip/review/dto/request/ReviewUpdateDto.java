package com.ssafy.trip.review.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ReviewUpdateDto {
    private int reviewNo;
    private String userId;
    private String title;
    //private int spotId;
    private String content;
    private float score;
    // 기존 사진들은 유지하면서 새로운 사진들만 추가하는 방식
    private List<String> deletePhotoUrls; // 삭제할 사진 URL 목록
}
