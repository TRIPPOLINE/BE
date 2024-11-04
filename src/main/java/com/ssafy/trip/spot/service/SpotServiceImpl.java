package com.ssafy.trip.spot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.mapper.SpotMapper;

@Service
public class SpotServiceImpl implements SpotService {
	
	@Autowired
	private SpotMapper spotMapper;
	
	public SpotServiceImpl(SpotMapper spotMapper) {
		this.spotMapper = spotMapper;
	}

	@Override
	public List<SidoDto> selectSidoList() {
		return spotMapper.selectSidoList();
	}

}
