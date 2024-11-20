package com.ssafy.trip.review.controller;

import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.RequestReview;
import com.ssafy.trip.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    // 해당 사용자가 작성한 리뷰 목록
    @GetMapping("/list")
    public ResponseEntity<?> listReview(@RequestParam Map<String, String> map){
        List<ReviewDto> list = reviewService.listReview(map);
        if(list == null || list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 리뷰 작성
    @PostMapping("/write")
    public ResponseEntity<?> writeReview(@RequestBody RequestReview requestReview){
        reviewService.writeReview(reviewDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 리뷰 수정
    @PostMapping("/modify")
    public ResponseEntity<?> modifyReview(@RequestBody ReviewDto reviewDto){
        reviewService.modifyReview(reviewDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 리뷰 삭제
    @PostMapping("/delete")
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto){
        reviewService.deleteReview(reviewDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
