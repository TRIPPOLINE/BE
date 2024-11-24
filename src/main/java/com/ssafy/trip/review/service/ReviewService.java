package com.ssafy.trip.review.service;

import com.ssafy.trip.review.dto.ReviewDto;
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
    void modifyReview(ReviewUpdateDto reviewDto);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);
    public List<ReviewResponseDto> searchReviews(ReviewSearchDto searchDto);
}
