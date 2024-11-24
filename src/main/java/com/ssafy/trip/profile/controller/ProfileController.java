package com.ssafy.trip.profile.controller;

import com.ssafy.trip.profile.dto.ProfileDto;
import com.ssafy.trip.profile.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // 사용자 프로필 조회
    @GetMapping("/select/{userId}")
    public ResponseEntity<?> searchProfile(@PathVariable String userId) {
        ProfileDto profile = profileService.selectProfileById(userId);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    // 프로필 생성
    @PostMapping("/insert")
    public ResponseEntity<?> insertProfile(
            @RequestPart(value = "profile") ProfileDto profileDto,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        profileService.insertProfile(profileDto, photo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 프로필 수정
    @PostMapping("/modify")
    public ResponseEntity<?> modifyProfile(
            @RequestPart(value = "profile") ProfileDto profileDto,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {
        profileService.modifyProfile(profileDto, photo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
