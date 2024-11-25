package com.ssafy.trip.review.mapper;

import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.ReviewLikeDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import com.ssafy.trip.review.dto.request.ReviewUpdateDto;
import com.ssafy.trip.review.dto.request.ReviewWriteDto;
import com.ssafy.trip.review.dto.response.ReviewResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
    List<ReviewResponseDto> searchReviews(@Param("keyword") String keyword,
                                          @Param("searchType") String searchType,
                                          @Param("offset") int offset,
                                          @Param("size") int size);

    List<ReviewResponseDto> getReviews(
            @Param("sortBy") String sortBy,
            @Param("offset") int offset,
            @Param("size") int size
    );

    ReviewLikeDto getLikeStatus(@Param("reviewNo") int reviewNo, @Param("userId") String userId);
    void insertLike(@Param("reviewNo") int reviewNo, @Param("userId") String userId);
    void deleteLike(@Param("reviewNo") int reviewNo, @Param("userId") String userId);
    int getLikeCount(@Param("reviewNo") int reviewNo);

    List<ReviewDto> listUserReview(Map<String, String> map);
    List<ReviewDto> listSpotReview(Map<String, String> map);
    void writeReview(ReviewWriteDto reviewWriteDto);
    void modifyReview(ReviewUpdateDto reviewDto);
    void deleteReview(ReviewDeleteDto reviewDeleteDto);
    void insertReviewPhoto(@Param("reviewNo") int reviewNo, @Param("photoUrl") String photoUrl);
}
