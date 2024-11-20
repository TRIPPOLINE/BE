package com.ssafy.trip.review.service;

import com.ssafy.trip.infrastructure.image.ImageUploader;
import com.ssafy.trip.plan.mapper.PlanMapper;
import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.RequestReview;
import com.ssafy.trip.review.mapper.ReviewMapper;
import com.ssafy.trip.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewMapper reviewMapper;
    private final ImageUploader imageUploader;
    private final UserMapper userMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper, ImageUploader imageUploader, UserMapper userMapper) {
        this.reviewMapper = reviewMapper;
        this.imageUploader = imageUploader;
        this.userMapper = userMapper;
    }

    @Override
    public List<ReviewDto> listReview(Map<String, String> map) {
        return reviewMapper.listReview(map);
    }

    @Transactional
    @Override
    public void writeReview(RequestReview requestReview, List<MultipartFile> photos) {

        log.info("유저 id : "+requestReview.getUserId());
        log.info("유저 존재 여부 : "+userMapper.selectUser(requestReview.getUserId()).toString());

        ReviewDto reviewDto = ReviewDto.builder()
                .userId(requestReview.getUserId())
                .spotId(requestReview.getSpotId())
                .content(requestReview.getContent())
                .score(requestReview.getScore())
                .tripAt(requestReview.getTripAt())
                .build();

        reviewMapper.writeReview(reviewDto);
        int reviewNo = reviewDto.getReviewNo();

        log.info("등록된 리뷰 번호 : "+reviewNo);

        uploadReviewPhotos(photos, reviewNo);
    }

    @Override
    public void modifyReview(ReviewDto reviewDto) {
        reviewMapper.modifyReview(reviewDto);
    }

    @Override
    public void deleteReview(ReviewDto reviewDto) {
        reviewMapper.deleteReview(reviewDto);
    }

    @Transactional
    public void uploadReviewPhotos(List<MultipartFile> photos, int reviewNo){
        if(photos!=null){
            for(MultipartFile photo : photos){
                String photoUrl = imageUploader.uploadImage(photo);
                reviewMapper.insertReviewPhoto(reviewNo, photoUrl);
            }
        }
    }
}
