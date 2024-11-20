package com.ssafy.trip.notice.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.global.util.PageResult;
import com.ssafy.trip.global.util.PaginationUtil;
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
	public PageResult<NoticeDto> listNotice(int page, int size) {
		Map<String, Object> params = PaginationUtil.createPaginationMap(page, size);
		List<NoticeDto> notices = noticeMapper.listNotice(params);
		int totalCount = noticeMapper.getTotalCount();
		return PaginationUtil.getPageResult(notices, page, size, totalCount);
	}

	@Override
	public PageResult<NoticeDto> searchNotice(int page, int size, String key, String word) {
		Map<String, Object> params = PaginationUtil.createPaginationMap(page, size);
		params.put("key", key);
		params.put("word", word);
		List<NoticeDto> notices = noticeMapper.searchNotice(params);
		int totalCount = noticeMapper.getTotalSearchCount(params);
		return PaginationUtil.getPageResult(notices, page, size, totalCount);
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
