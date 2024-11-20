package com.ssafy.trip.review.service;

import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import com.ssafy.trip.review.dto.request.ReviewWriteDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<ReviewDto> listReview(Map<String, String> map);
    void writeReview(ReviewWriteDto reviewWriteDto, List<MultipartFile> photos);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);
}
