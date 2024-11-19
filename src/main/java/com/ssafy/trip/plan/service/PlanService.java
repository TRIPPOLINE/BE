package com.ssafy.trip.plan.service;

import com.ssafy.trip.notice.dto.NoticeDto;
import com.ssafy.trip.plan.dto.PlaceDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface PlanService {

    List<PlaceDto> listPlace(Map<String, String> map);
    void selectPlace(PlaceDto placeDto);
    void deletePlace(PlaceDto placeDto);
    void modifyPlace(PlaceDto placeDto);
    //List<PlaceDto> listPlace(int planId);
}
