package com.ssafy.trip.review.service;

import com.ssafy.trip.infrastructure.image.ImageUploader;
import com.ssafy.trip.review.dto.ReviewDto;
import com.ssafy.trip.review.dto.ReviewLikeDto;
import com.ssafy.trip.review.dto.request.ReviewDeleteDto;
import com.ssafy.trip.review.dto.request.ReviewSearchDto;
import com.ssafy.trip.review.dto.request.ReviewUpdateDto;
import com.ssafy.trip.review.dto.request.ReviewWriteDto;
import com.ssafy.trip.review.dto.response.ReviewResponseDto;
import com.ssafy.trip.review.mapper.ReviewMapper;
import com.ssafy.trip.user.mapper.UserMapper;
import java.time.LocalDateTime;
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

//        // ReviewWriteDto에서 ReviewDto로 변환 (title 포함)
//        ReviewDto reviewDto = ReviewDto.builder()
//                .userId(reviewWriteDto.getUserId())
//                .spotId(reviewWriteDto.getSpotId())
//                .title(reviewWriteDto.getTitle())  // title 추가
//                .content(reviewWriteDto.getContent())
//                .score(reviewWriteDto.getScore())
//                .writeAt(LocalDateTime.now())
//                .build();

        //리뷰 게시
        reviewMapper.writeReview(reviewWriteDto);  // reviewNo가 자동 생성됨
        int reviewNo = reviewWriteDto.getReviewNo();  // 삽입 후 reviewNo 확인

        log.info("등록된 리뷰 번호 : "+reviewNo);

        //첨부 사진들 게시
        uploadReviewPhotos(photos, reviewNo);
    }


    @Transactional
    @Override
    public void modifyReview(ReviewUpdateDto reviewDto, List<MultipartFile> newPhotos) {
        // 기본 리뷰 정보 업데이트
        reviewMapper.modifyReview(reviewDto);

        // 삭제할 사진들 처리
        if (reviewDto.getDeletePhotoUrls() != null) {
            for (String photoUrl : reviewDto.getDeletePhotoUrls()) {
                reviewMapper.deleteReviewPhoto(reviewDto.getReviewNo(), photoUrl);
            }
        }

        // 새로운 사진들 업로드
        if (newPhotos != null) {
            uploadReviewPhotos(newPhotos, reviewDto.getReviewNo());
        }
    }

    @Override
    public void deleteReview(ReviewDeleteDto reviewDeleteDto) {
        reviewMapper.deleteReview(reviewDeleteDto);
    }

    @Override
    public List<ReviewResponseDto> searchReviews(ReviewSearchDto searchDto) {
        int offset = (searchDto.getPage() - 1) * searchDto.getSize();
        return reviewMapper.searchReviews(
                searchDto.getKeyword(),
                searchDto.getSearchType(),
                offset,
                searchDto.getSize()
        );
    }

    @Override
    public List<ReviewResponseDto> getReviews(String sortBy, int page, int size) {
        int offset = (page - 1) * size;
        return reviewMapper.getReviews(sortBy, offset, size);
    }

    @Override
    @Transactional
    public ReviewLikeDto toggleLike(int reviewNo, String userId) {
        ReviewLikeDto likeStatus = reviewMapper.getLikeStatus(reviewNo, userId);

        if (likeStatus.isLiked()) {
            reviewMapper.deleteLike(reviewNo, userId);
        } else {
            reviewMapper.insertLike(reviewNo, userId);
        }

        int newLikeCount = reviewMapper.getLikeCount(reviewNo);
        likeStatus.setLikeCount(newLikeCount);
        likeStatus.setLiked(!likeStatus.isLiked());

        return likeStatus;
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
    @Transactional
    public void deleteReviewPhoto(int reviewNo, String photoUrl) {
        reviewMapper.deleteReviewPhoto(reviewNo, photoUrl);
    }
}
