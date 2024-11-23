package com.ssafy.trip.review.mapper;

import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import com.ssafy.trip.review.dto.request.ReviewUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    List<ReviewDto> listUserReview(Map<String, String> map);
    List<ReviewDto> listSpotReview(Map<String, String> map);
    void writeReview(ReviewDto reviewDto);
    void modifyReview(ReviewUpdateDto reviewDto);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);
    void insertReviewPhoto(@Param("reviewNo") int reviewNo, @Param("photoUrl") String photoUrl);
}
