package com.ssafy.trip.spot.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.dto.SigunguDto;
import com.ssafy.trip.spot.dto.SpotDto;
import com.ssafy.trip.spot.dto.SpotTypeDto;
import com.ssafy.trip.spot.service.SpotService;

@RestController
@RequestMapping("/spot")
public class SpotController {
	@Autowired
	private SpotService spotService;
	
	public SpotController(SpotService spotService) {
		this.spotService = spotService;
	}
	
	@GetMapping("/sidos")
	public ResponseEntity<?> selectAllSidos(){
		List<SidoDto> sidoList = spotService.selectAllSidos();
		if(sidoList==null || sidoList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(sidoList, HttpStatus.OK);
	}
	
	@GetMapping("/sigungus/{sidoCode}")
	public ResponseEntity<?> selectBySido(@PathVariable int sidoCode){
		List<SigunguDto> sigunguList = spotService.selectBySido(sidoCode);
		if(sigunguList==null || sigunguList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(sigunguList, HttpStatus.OK);
	}
	
	@GetMapping("/sigungu/{sigunguCode}/attraction")
	public ResponseEntity<?> selectBySigungu(@PathVariable int sigunguCode){
		List<SpotDto> spotList = spotService.selectBySigungu(sigunguCode);
		if(spotList==null || spotList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(spotList, HttpStatus.OK);
	}
	
	@GetMapping("/contenttypes")
	public ResponseEntity<?> selectAllSpotTypes(){
		List<SpotTypeDto> spotTypeList = spotService.selectAllSpotTypes();
		if(spotTypeList==null || spotTypeList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(spotTypeList, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(
			@RequestParam(required=false) Integer sidoCode,
			@RequestParam(required=false) Integer sigunguCode,
			@RequestParam(required=false) Integer contentTypeId
			){
		List<SpotDto> spotList = spotService.selectSpotBySidoAndSigunguAndContentType(sidoCode, sigunguCode, contentTypeId);
		if(spotList==null || spotList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(spotList, HttpStatus.OK);
	}
}
