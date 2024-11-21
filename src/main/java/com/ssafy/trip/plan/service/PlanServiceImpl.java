package com.ssafy.trip.plan.service;

import com.ssafy.trip.plan.dto.PlaceDto;
import com.ssafy.trip.plan.dto.PlanDto;
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
    public void modifyOrder(PlaceDto placeDto) {
        planMapper.modifyOrder(placeDto);
    }

    @Override
    public int insertPlan(PlanDto planDto) {
        return planMapper.insertPlan(planDto);
    }

    @Override
    public void deletePlan(PlanDto planDto) {
        planMapper.deletePlan(planDto);
    }
}
