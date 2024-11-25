package com.ssafy.trip.spot.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SpotDto {
	private int spotId;
    private String title;
    private int contentTypeId;
    private int sidoCode;
    private int sigunguCode;
    private String imagePath1;
    private String imagePath2;
    private int mapLevel;
    private double latitude;
    private double longitude;
    private String frontAddress;
    private String rearAddress;
    private BigDecimal avgScore;
    private int reviewCount;
}
