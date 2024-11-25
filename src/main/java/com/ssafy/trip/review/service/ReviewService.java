package com.ssafy.trip.review.service;

import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.ReviewLikeDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import com.ssafy.trip.review.dto.request.ReviewSearchDto;
import com.ssafy.trip.review.dto.request.ReviewUpdateDto;
import com.ssafy.trip.review.dto.request.ReviewWriteDto;
import com.ssafy.trip.review.dto.response.ReviewResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<ReviewDto> listUserReview(Map<String, String> map);
    List<ReviewDto> listSpotReview(Map<String, String> map);
    void writeReview(ReviewWriteDto reviewWriteDto, List<MultipartFile> photos);
    // 리뷰 수정 메서드 시그니처 변경
    void modifyReview(ReviewUpdateDto reviewDto, List<MultipartFile> newPhotos);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);
    List<ReviewResponseDto> searchReviews(ReviewSearchDto searchDto);
    List<ReviewResponseDto> getReviews(String sortBy, int page, int size);
    ReviewLikeDto toggleLike(int reviewNo, String userId);
    // 리뷰 사진 관련 메서드 추가
    void deleteReviewPhoto(int reviewNo, String photoUrl);
    void uploadReviewPhotos(List<MultipartFile> photos, int reviewNo);
}