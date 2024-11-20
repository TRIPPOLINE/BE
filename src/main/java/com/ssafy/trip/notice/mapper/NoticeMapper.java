package com.ssafy.trip.notice.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.global.util.PageResult;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.notice.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	void writeNotice(NoticeDto noticeDto);
	List<NoticeDto> listNotice(Map<String, Object> params);
	List<NoticeDto> searchNotice(Map<String, Object> params);
	int getTotalCount();
	int getTotalSearchCount(Map<String, Object> params);
	NoticeDto selectNotice(int noticeNo);
	void modifyNotice(NoticeDto noticeDto);
	void deleteNotice(int noticeNo);

	int getTotalNoticeCount(Map<String, Object> map);
}
