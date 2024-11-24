package com.ssafy.trip.profile.service;

import com.ssafy.trip.profile.dto.ProfileDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileDto selectProfileById(String userId);
    void insertProfile(ProfileDto profileDto, MultipartFile photo);
    void modifyProfile(ProfileDto profileDto, MultipartFile photo);
}
