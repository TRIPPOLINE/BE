package com.ssafy.trip.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.notice.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	
	void writeNotice(NoticeDto noticeDto);
	List<NoticeDto> listNotice(Map<String, Object> map);
	int getTotalNoticeCount(Map<String, Object> map);
	NoticeDto selectNotice(int noticeNo);
	void modifyNotice(NoticeDto noticeDto);
	void deleteNotice(int noticeNo);

}
