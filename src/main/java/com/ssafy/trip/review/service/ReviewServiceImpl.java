package com.ssafy.trip.review.service;

import com.ssafy.trip.infrastructure.image.ImageUploader;
import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import com.ssafy.trip.review.dto.request.ReviewUpdateDto;
import com.ssafy.trip.review.dto.request.ReviewWriteDto;
import com.ssafy.trip.review.mapper.ReviewMapper;
import com.ssafy.trip.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
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
    public List<ReviewDto> listUserReview(Map<String, String> map) {
        return reviewMapper.listUserReview(map);
    }

    @Override
    public List<ReviewDto> listSpotReview(Map<String, String> map) {
        return reviewMapper.listSpotReview(map);
    }

    @Transactional
    @Override
    public void writeReview(ReviewWriteDto reviewWriteDto, List<MultipartFile> photos) {

        log.info("유저 id : "+ reviewWriteDto.getUserId());
        log.info("유저 존재 여부 : "+userMapper.selectUser(reviewWriteDto.getUserId()).toString());

        ReviewDto reviewDto = ReviewDto.builder()
                .userId(reviewWriteDto.getUserId())
                .spotId(reviewWriteDto.getSpotId())
                .content(reviewWriteDto.getContent())
                .score(reviewWriteDto.getScore())
                .tripAt(reviewWriteDto.getTripAt())
                .build();

        reviewMapper.writeReview(reviewDto);
        int reviewNo = reviewDto.getReviewNo();

        log.info("등록된 리뷰 번호 : "+reviewNo);

        uploadReviewPhotos(photos, reviewNo);
    }

    @Override
    public void modifyReview(ReviewUpdateDto reviewDto) {
        reviewMapper.modifyReview(reviewDto);
    }

    @Override
    public void deleteReview(ReviewDeleteDto reviewDeleteDto) {
        reviewMapper.deleteReview(reviewDeleteDto);
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
