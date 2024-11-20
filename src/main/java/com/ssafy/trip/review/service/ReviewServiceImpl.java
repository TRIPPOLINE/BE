package com.ssafy.trip.review.service;

import com.ssafy.trip.plan.mapper.PlanMapper;
import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.RequestReview;
import com.ssafy.trip.review.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewMapper reviewMapper;
    private final

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDto> listReview(Map<String, String> map) {
        return reviewMapper.listReview(map);
    }

    @Override
    public void writeReview(RequestReview requestReview) {
        ReviewDto reviewDto = ReviewDto.builder()
                .userId(requestReview.getUserId())
                .spotId(requestReview.getSpotId())
                .content(requestReview.getContent())
                .score(requestReview.getScore())
                .tripAt(requestReview.getTripAt())
                .build();

        reviewMapper.writeReview(reviewDto);

        List<MultipartFile> photos = requestReview.getPhotos();
        if(photos!=null){
            for(MultipartFile photo : photos){
                String photoUrl =
            }
        }
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
