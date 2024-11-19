package com.ssafy.trip.review.service;

import com.ssafy.trip.plan.mapper.PlanMapper;
import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> listReview(Map<String, String> map) {
        return reviewMapper.listReview(map);
    }

    @Override
    public void writeReview(ReviewDto reviewDto) {
        reviewMapper.writeReview(reviewDto);
    }

    @Override
    public void modifyReview(ReviewDto reviewDto) {
        reviewMapper.modifyReview(reviewDto);
    }

    @Override
    public void deleteReview(ReviewDto reviewDto) {
        reviewMapper.deleteReview(reviewDto);
    }
}
