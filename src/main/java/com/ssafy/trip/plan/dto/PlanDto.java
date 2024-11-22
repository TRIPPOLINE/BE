package com.ssafy.trip.plan.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PlanDto {
    private int planId;
    private String userId;
    private Date planAt;
    private Date tripAt;
}
