package com.ssafy.trip.review.service;

import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.RequestReview;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<ReviewDto> listReview(Map<String, String> map);
    void writeReview(RequestReview requestReview);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(ReviewDto reviewDto);
}
