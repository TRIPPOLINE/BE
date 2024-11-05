package com.ssafy.trip.spot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import com.ssafy.trip.spot.dto.SidoDto;

@Mapper
public interface SpotMapper {
	List<SidoDto> selectSidoList();
}
