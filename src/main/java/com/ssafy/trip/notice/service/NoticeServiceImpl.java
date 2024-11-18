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
	private final int DEFAULT_PAGE_SIZE = 10; //TODO : 리팩터링
	
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
	public List<NoticeDto> listNotice(Map<String, Object> map) {
		int pgno = (int) map.get("pgno");
		int sizePerPage = (int) map.get("sizePerPage");
		int offset = (pgno - 1) * sizePerPage;

		map.put("offset", offset);
		map.put("limit", sizePerPage);

		return noticeMapper.listNotice(map);
	}

	@Override
	public int getTotalNoticeCount(Map<String, Object> map) {
		return noticeMapper.getTotalNoticeCount(map);
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
