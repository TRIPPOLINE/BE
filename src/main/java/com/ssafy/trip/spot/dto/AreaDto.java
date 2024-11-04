package com.ssafy.trip.spot.dto;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AreaDto {
	private int areacode;
    private String areaname;
}
