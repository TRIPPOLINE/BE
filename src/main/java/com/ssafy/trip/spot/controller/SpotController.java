package com.ssafy.trip.spot.controller;

import com.ssafy.trip.spot.dto.request.NearbySearchRequest;
import com.ssafy.trip.spot.dto.response.NearbySearchResponse;
import java.io.IOException;
import java.util.List;


import com.ssafy.trip.infrastructure.image.ImageUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.spot.dto.SidoDto;
import com.ssafy.trip.spot.dto.SigunguDto;
import com.ssafy.trip.spot.dto.SpotDto;
import com.ssafy.trip.spot.dto.SpotTypeDto;
import com.ssafy.trip.spot.service.SpotService;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/spot")
@PreAuthorize("hasRole('ROLE_USER')")
public class SpotController {
	private final SpotService spotService;
	
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

	@PostMapping("/currentLocation")
	public ResponseEntity<NearbySearchResponse> nearbySearchSpot(@RequestBody NearbySearchRequest request){
		List<SpotDto> nearbySpots = spotService.selectSpotsInBounds(
				request.getMinLatitude(),
				request.getMaxLatitude(),
				request.getMinLongitude(),
				request.getMaxLongitude(),
				request.getCursor(),
				request.getLimit()
		);

		Integer nextCursor = nearbySpots.isEmpty()?null:nearbySpots.get(nearbySpots.size()-1).getSpotId();

		NearbySearchResponse response = new NearbySearchResponse(nearbySpots, nextCursor);

		return ResponseEntity.ok(response);
	}
}
