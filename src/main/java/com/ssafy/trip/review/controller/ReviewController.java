package com.ssafy.trip.review.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.ReviewLikeDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import com.ssafy.trip.review.dto.request.ReviewSearchDto;
import com.ssafy.trip.review.dto.request.ReviewUpdateDto;
import com.ssafy.trip.review.dto.request.ReviewWriteDto;
import com.ssafy.trip.review.dto.response.ReviewResponseDto;
import com.ssafy.trip.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    // 해당 사용자가 작성한 리뷰 목록
    @GetMapping("/userlist")
    public ResponseEntity<?> listUserReview(@RequestParam String userId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        List<ReviewDto> list = reviewService.listUserReview(paramMap);
        if(list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    // 해당 여행지의 리뷰 목록
    @GetMapping("/spotlist")
    public ResponseEntity<?> listSpotReview(@RequestParam Map<String, String> map){
        List<ReviewDto> list = reviewService.listSpotReview(map);
        if(list == null || list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 리뷰 작성
    @PostMapping("/write")
    public ResponseEntity<?> writeReview(@RequestPart String requestReviewJson, @RequestPart List<MultipartFile> photos) throws JsonProcessingException {
        ReviewWriteDto reviewWriteDto = new ObjectMapper().readValue(requestReviewJson, ReviewWriteDto.class);
        reviewService.writeReview(reviewWriteDto, photos);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 리뷰 수정
    @PostMapping("/modify")
    public ResponseEntity<?> modifyReview(@RequestBody ReviewUpdateDto ReviewUpdateDto){
        reviewService.modifyReview(ReviewUpdateDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 리뷰 삭제
    @PostMapping("/delete")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDeleteDto reviewDeleteDto){
        reviewService.deleteReview(reviewDeleteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getReviews(
            @RequestParam(defaultValue = "likes") String sortBy,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ReviewResponseDto> reviews = reviewService.getReviews(sortBy, page, size);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReviewResponseDto>> searchReviews(
            @RequestParam String keyword,
            @RequestParam String searchType,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        ReviewSearchDto searchDto = ReviewSearchDto.builder()
                .keyword(keyword)
                .searchType(searchType)
                .page(page)
                .size(size)
                .build();
        List<ReviewResponseDto> reviews = reviewService.searchReviews(searchDto);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/like")
    public ResponseEntity<ReviewLikeDto> toggleLike(@RequestBody ReviewLikeDto likeDto) {
        log.info("좋아요 요청 : "+likeDto.toString());
        ReviewLikeDto result = reviewService.toggleLike(likeDto.getReviewNo(), likeDto.getUserId());
        return ResponseEntity.ok(result);
    }
}
