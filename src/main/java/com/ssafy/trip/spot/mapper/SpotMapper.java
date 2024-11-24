package com.ssafy.trip.spot.mapper;

import java.util.List;

import com.ssafy.trip.spot.dto.request.SpotFilterRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.dto.SigunguDto;
import com.ssafy.trip.spot.dto.SpotDto;
import com.ssafy.trip.spot.dto.SpotTypeDto;

@Mapper
public interface SpotMapper {
	List<SidoDto> selectAllSidos();
	List<SigunguDto> selectBySido(@Param("sidoCode") int sidoCode);
	List<SpotDto> selectBySigungu(@Param("sigunguCode") int sigunguCode);
	List<SpotTypeDto> selectAllSpotTypes();
	List<SpotDto> selectBySpotType(@Param("contentTypeId") int contentTypeId);
	List<SpotDto> selectSpotBySidoAndSigunguAndContentType(SpotFilterRequest spotFilterRequest);
	List<SpotDto> selectSpotsInBounds(
			@Param("minLat") double minLat,
			@Param("maxLat") double maxLat,
			@Param("minLng") double minLng,
			@Param("maxLng") double maxLng,
			@Param("cursor") Integer cursor,
			@Param("limit") int limit
	);
	SpotDto selectSpotById(int spotId);
}
