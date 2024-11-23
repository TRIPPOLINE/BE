package com.ssafy.trip.spot.service;

import java.util.List;

import com.ssafy.trip.spot.dto.request.SpotSearchRequestDto;
import org.apache.ibatis.annotations.Param;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.dto.SigunguDto;
import com.ssafy.trip.spot.dto.SpotDto;
import com.ssafy.trip.spot.dto.SpotTypeDto;

public interface SpotService {
	List<SidoDto> selectAllSidos();
	List<SigunguDto> selectBySido(int sidoCode);
	List<SpotDto> selectBySigungu(int sigunguCode);
	List<SpotTypeDto> selectAllSpotTypes();
	List<SpotDto> selectBySpotType(int contentTypeId);
	List<SpotDto> selectSpotBySidoAndSigunguAndContentTypeAndKeyword(SpotSearchRequestDto spotSearchRequestDto);
	List<SpotDto> selectSpotsInBounds(
			@Param("minLat") double minLat,
			@Param("maxLat") double maxLat,
			@Param("minLng") double minLng,
			@Param("maxLng") double maxLng,
			@Param("cursor") Integer cursor,
			@Param("limit") int limit
	);
}
