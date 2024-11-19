package com.ssafy.trip.auth.mapper;

import com.ssafy.trip.auth.dto.RefreshTokenDto;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshMapper {
    Optional<RefreshTokenDto> selectRefreshToken(String refreshToken);
    boolean existsByKeyUserId(String userId);
    void deleteByKeyUserId(String userId);
    void save(RefreshTokenDto refreshTokenDto);
}
