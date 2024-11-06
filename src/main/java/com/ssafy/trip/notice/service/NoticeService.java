package com.ssafy.trip.notice.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.notice.dto.NoticeDto;

public interface NoticeService {
	
	void writeNotice(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listNotice(Map<String, String> map) throws Exception;
	NoticeDto selectNotice(int noticeNo) throws Exception;
	void modifyNotice(NoticeDto noticeDto) throws Exception;
	void deleteNotice(int noticeNo, String path) throws Exception;
	
}
