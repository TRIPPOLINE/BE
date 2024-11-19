package com.ssafy.trip.plan.controller;

import com.ssafy.trip.plan.dto.PlaceDto;
import com.ssafy.trip.plan.dto.PlanDto;
import com.ssafy.trip.plan.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    public PlanController(PlanService placeService) {
        this.planService = placeService;
    }

    // 해당 plan의 선택한 여행지 목록
    @GetMapping("/list")
    public ResponseEntity<?> listNotice(@RequestParam Map<String, String> map){
        List<PlaceDto> list = planService.listPlace(map);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 해당 plan의 여행지 선택
    @PostMapping("/select")
    public ResponseEntity<?> selectPlace(@RequestBody PlaceDto placeDto) {
        planService.selectPlace(placeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 해당 plan의 여행지 삭제
    @PostMapping("/deletePlace")
    public ResponseEntity<?> deletePlace(@RequestBody PlaceDto placeDto) {
        planService.deletePlace(placeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 해당 plan의 여행지 순서 변경
    @PostMapping("/order")
    public ResponseEntity<?> modifyOrder(@RequestBody PlaceDto placeDto) {
        planService.modifyOrder(placeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 여행 계획 생성
    @PostMapping("/insert")
    public ResponseEntity<?> insertPlan(@RequestBody PlanDto planDto){
        planService.insertPlan(planDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 여행 계획 삭제
    @PostMapping("/deletePlan")
    public ResponseEntity<?> deletePlan(@RequestBody PlanDto planDto){
        planService.deletePlan(planDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
