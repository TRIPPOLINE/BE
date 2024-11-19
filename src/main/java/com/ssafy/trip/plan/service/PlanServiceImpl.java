package com.ssafy.trip.plan.service;

import com.ssafy.trip.plan.dto.PlaceDto;
import com.ssafy.trip.plan.mapper.PlanMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired

    private PlanMapper planMapper;

    public PlanServiceImpl(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public List<PlaceDto> listPlace(Map<String, String> map) {
        return planMapper.listPlace(map);
    }

    @Override
    public void selectPlace(PlaceDto placeDto) {
        planMapper.selectPlace(placeDto);
    }

    @Override
    public void deletePlace(PlaceDto placeDto) {
        planMapper.deletePlace(placeDto);
    }

    @Override
    public void modifyPlace(PlaceDto placeDto) {
        planMapper.modifyPlace(placeDto);
    }
//
//    @Override
//    public List<PlaceDto> listPlace(int planId) {
//        return planMapper.listPlace(planId);
//    }
}
