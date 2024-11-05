package com.ssafy.trip.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.notice.dto.NoticeDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService{

	@Override
	public void writeNotice(NoticeDto noticeDto) throws Exception {
		
	}

	@Override
	public List<NoticeDto> listNotice(Map<String, String> map) throws Exception {
		return null;
	}

	@Override
	public NoticeDto selectNotice(int noticeNo) throws Exception {
		return null;
	}

	@Override
	public void modifyNotice(NoticeDto noticeDto) throws Exception {
		
	}

	@Override
	public void deleteNotice(int noticeNo, String path) throws Exception {
		
	}

}
