package com.ssafy.trip.review.mapper;

import com.ssafy.trip.review.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    List<ReviewDto> listReview(Map<String, String> map);
    void writeReview(ReviewDto reviewDto);
    void modifyReview(ReviewDto reviewDto);
    void deleteReview(ReviewDto reviewDto);

}
