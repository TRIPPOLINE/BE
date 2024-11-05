package com.ssafy.trip.spot.service;

import java.util.List;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.dto.SigunguDto;

public interface SpotService {
	List<SidoDto> selectAllSidos();
	List<SigunguDto> selectBySido(int sidoCode);
}
