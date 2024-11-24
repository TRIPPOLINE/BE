package com.ssafy.trip.profile.service;

import com.ssafy.trip.infrastructure.image.ImageUploader;
import com.ssafy.trip.profile.dto.ProfileDto;
import com.ssafy.trip.profile.mapper.ProfileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileMapper profileMapper;
    private final ImageUploader imageUploader;

    public ProfileServiceImpl(ProfileMapper profileMapper, ImageUploader imageUploader) {
        this.profileMapper = profileMapper;
        this.imageUploader = imageUploader;
    }

    @Override
    public ProfileDto selectProfileById(String userId) {
        return profileMapper.selectProfileById(userId);
    }

    @Override
    @Transactional
    public void insertProfile(ProfileDto profileDto, MultipartFile photo) {
        if (photo != null && !photo.isEmpty()) {
            String photoUrl = imageUploader.uploadImage(photo);
            profileDto.setPhoto(photoUrl);
        }
        profileMapper.insertProfile(profileDto);
    }

    @Override
    @Transactional
    public void modifyProfile(ProfileDto profileDto, MultipartFile photo) {
        if (photo != null && !photo.isEmpty()) {
            String photoUrl = imageUploader.uploadImage(photo);
            profileDto.setPhoto(photoUrl);
        }
        profileMapper.modifyProfile(profileDto);
    }
}
