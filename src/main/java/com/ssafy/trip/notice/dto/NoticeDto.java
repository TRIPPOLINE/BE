package com.ssafy.trip.notice.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeDto {
	
	private int noticeNo;
	private String userId;
	private String title;
	private String contents;
	private Date createdAt;
	private Date updatedAt;
	
}
