package com.ssafy.trip.review.service;

import com.ssafy.trip.review.dto.ReviewDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<ReviewDto> listReview(Map<String, String> map);
    void writeReview(ReviewDto reviewDto);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(ReviewDto reviewDto);
}
