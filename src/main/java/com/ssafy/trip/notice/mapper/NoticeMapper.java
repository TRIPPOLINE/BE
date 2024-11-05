package com.ssafy.trip.notice.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.notice.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	
	void writeNotice(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listNotice(Map<String, String> map) throws Exception;
	NoticeDto selectNotice(int noticeNo) throws Exception;
	void modifyNotice(NoticeDto noticeDto) throws Exception;
	void deleteNotice(int noticeNo, String path) throws Exception;

}
