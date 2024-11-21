package com.ssafy.trip.plan.mapper;

import com.ssafy.trip.notice.dto.NoticeDto;
import com.ssafy.trip.plan.dto.PlaceDto;
import com.ssafy.trip.plan.dto.PlanDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlanMapper {

    List<PlaceDto> listPlace(Map<String, String> map);
    void selectPlace(PlaceDto placeDto);
    void deletePlace(PlaceDto placeDto);
    void modifyOrder(PlaceDto placeDto);
    int insertPlan(PlanDto planDto);
    void deletePlan(PlanDto planDto);

}
