package com.ssafy.trip.spot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.dto.SigunguDto;

@Mapper
public interface SpotMapper {
	List<SidoDto> selectAllSidos();
	List<SigunguDto> selectBySido(@Param("sidoCode") int sidoCode);
}
