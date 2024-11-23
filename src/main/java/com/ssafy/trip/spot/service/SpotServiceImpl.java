package com.ssafy.trip.spot.service;

import java.util.Arrays;
import java.util.List;

import com.ssafy.trip.spot.dto.request.SpotSearchRequestDto;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.dto.SigunguDto;
import com.ssafy.trip.spot.dto.SpotDto;
import com.ssafy.trip.spot.dto.SpotTypeDto;
import com.ssafy.trip.spot.mapper.SpotMapper;

@Service
public class SpotServiceImpl implements SpotService {
	
	@Autowired
	private SpotMapper spotMapper;
	
	public SpotServiceImpl(SpotMapper spotMapper) {
		this.spotMapper = spotMapper;
	}

	@Override
	public List<SidoDto> selectAllSidos() {
		return spotMapper.selectAllSidos();
	}

	@Override
	public List<SigunguDto> selectBySido(int sidoCode) {
		return spotMapper.selectBySido(sidoCode);
	}

	@Override
	public List<SpotDto> selectBySigungu(int sigunguCode) {
		return spotMapper.selectBySigungu(sigunguCode);
	}

	@Override
	public List<SpotTypeDto> selectAllSpotTypes() {
		return spotMapper.selectAllSpotTypes();
	}

	@Override
	public List<SpotDto> selectBySpotType(int contentTypeId) {
		return spotMapper.selectBySpotType(contentTypeId);
	}

	@Override
	public List<SpotDto> selectSpotBySidoAndSigunguAndContentTypeAndKeyword(SpotSearchRequestDto spotSearchRequestDto) {
		String processedKeyword = processKeyword(spotSearchRequestDto.getKeyword());

		return spotMapper.selectSpotBySidoAndSigunguAndContentType(
				SpotSearchRequestDto.builder()
						.areaCode(spotSearchRequestDto.getAreaCode())
						.siGunGuCode(spotSearchRequestDto.getSiGunGuCode())
						.contentTypeId(spotSearchRequestDto.getContentTypeId())
						.keyword(processedKeyword)
						.build()
		);
	}

	@Override
	public List<SpotDto> selectSpotsInBounds(double minLat, double maxLat, double minLng, double maxLng, Integer cursor, int limit) {
		return spotMapper.selectSpotsInBounds(minLat, maxLat, minLng, maxLng, cursor, limit);
	}

	private String processKeyword(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return null;
		}
		return Arrays.stream(keyword.split("\\s+"))
				.map(word -> "+" + word + "*")
				.collect(Collectors.joining(" "));
	}
}
