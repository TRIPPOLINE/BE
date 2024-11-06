package com.ssafy.trip.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.notice.dto.NoticeDto;
import com.ssafy.trip.notice.mapper.NoticeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}

	@Override
	public void writeNotice(NoticeDto noticeDto) {
		noticeMapper.writeNotice(noticeDto);
	}

	@Override
	public List<NoticeDto> listNotice(Map<String, String> map) {
		return noticeMapper.listNotice(map);
	}

	@Override
	public NoticeDto selectNotice(int noticeNo) {
		return noticeMapper.selectNotice(noticeNo);
	}

	@Override
	public void modifyNotice(NoticeDto noticeDto)  {
		noticeMapper.modifyNotice(noticeDto);
	}

	@Override
	public void deleteNotice(int noticeNo) {
		noticeMapper.deleteNotice(noticeNo);
	}

}
