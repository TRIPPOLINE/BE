package com.ssafy.trip.profile.mapper;

import com.ssafy.trip.profile.dto.ProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProfileMapper {
    ProfileDto selectProfileById(String userId);
    void insertProfile(ProfileDto profileDto);
    void modifyProfile(ProfileDto profileDto);
    void updateProfilePhoto(@Param("userId") String userId, @Param("photoUrl") String photoUrl);
}
