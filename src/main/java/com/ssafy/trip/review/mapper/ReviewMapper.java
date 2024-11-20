package com.ssafy.trip.review.mapper;

import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    List<ReviewDto> listReview(Map<String, String> map);
    int writeReview(ReviewDto reviewDto);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);
    void insertReviewPhoto(@Param("reviewNo") int reviewNo, @Param("photoUrl") String photoUrl);
}
