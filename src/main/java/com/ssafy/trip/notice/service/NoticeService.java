package com.ssafy.trip.notice.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.global.util.PageResult;
import com.ssafy.trip.notice.dto.NoticeDto;

public interface NoticeService {
	void writeNotice(NoticeDto noticeDto);
	PageResult<NoticeDto> listNotice(int page, int size);
	PageResult<NoticeDto> searchNotice(int page, int size, String key, String word);
	NoticeDto selectNotice(int noticeNo);
	void modifyNotice(NoticeDto noticeDto);
	void deleteNotice(int noticeNo);
	int getTotalNoticeCount(Map<String, Object> map);
}
