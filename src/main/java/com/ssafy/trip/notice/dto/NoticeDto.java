package com.ssafy.trip.notice.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeDto {
	
	private int notice_no;
	private String user_id;
	private String title;
	private String contents;
	private Date created_at;
	private Date updated_at;
	
}
