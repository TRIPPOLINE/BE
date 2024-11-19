package com.ssafy.trip.plan.controller;


import com.ssafy.trip.notice.dto.NoticeDto;
import com.ssafy.trip.plan.dto.PlaceDto;
import com.ssafy.trip.plan.service.PlanService;
import com.ssafy.trip.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    public PlanController(PlanService placeService) {
        this.planService = placeService;
    }
    
    // place 테이블에 선택한 여행지 정보
    // plan 테이블에 사용자별 여행 계획


    // 선택한 여행지 목록
    @GetMapping("/list")
    public ResponseEntity<?> listNotice(@RequestParam Map<String, String> map){
        List<PlaceDto> list = planService.listPlace(map);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 여행지 선택
    @PostMapping("/select")
    public ResponseEntity<?> selectPlace(@RequestBody PlaceDto placeDto) {
        planService.selectPlace(placeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 여행지 삭제
    @PostMapping("/select")
    public ResponseEntity<?> deletePlace(@RequestBody PlaceDto placeDto) {
        planService.deletePlace(placeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 여행지 순서 변경
    @PostMapping("/order")
    public ResponseEntity<?> modifyOrder(@RequestBody PlaceDto placeDto) {
        planService.modifyPlace(placeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 여행 계획 끝


}
