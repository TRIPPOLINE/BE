package com.ssafy.trip.notice.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.notice.dto.NoticeDto;

public interface NoticeService {
	
	void writeNotice(NoticeDto noticeDto);
	List<NoticeDto> listNotice(Map<String, Object> map);
	int getTotalNoticeCount(Map<String, Object> map);
	NoticeDto selectNotice(int noticeNo);
	void modifyNotice(NoticeDto noticeDto);
	void deleteNotice(int noticeNo);
	
}
