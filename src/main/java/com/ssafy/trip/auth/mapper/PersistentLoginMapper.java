package com.ssafy.trip.auth.mapper;

import com.ssafy.trip.auth.dto.PersistentLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface PersistentLoginMapper {
    void createNewToken(PersistentLogin token);
    PersistentLogin getTokenForSeries(String seriesId);
    void updateToken(String series, String tokenValue, Date lastUsed);
    void removeUserTokens(String username);
}