package com.ssafy.trip.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.notice.dto.NoticeDto;
import com.ssafy.trip.notice.service.NoticeService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	// 공지사항 목록
	// 커서 기반 페이지네이션
	@GetMapping("/list")
	public ResponseEntity<?> listNotice(@RequestParam Map<String, String> map){
		List<NoticeDto> list = noticeService.listNotice(map);
		if(list==null || list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 공지사항 등록 
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody NoticeDto noticeDto, HttpSession session) {
		//TODO 관리자 인증하기 추가
	    noticeService.writeNotice(noticeDto);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// 공지사항 수정 페이지
	@GetMapping("/modify/{notice_no}")
	public ResponseEntity<?> getModify(@PathVariable int notice_no) {
	    NoticeDto noticeDto = noticeService.selectNotice(notice_no);
	    if(noticeDto==null) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(noticeDto, HttpStatus.OK);
	}
	
	// 공지사항 수정
	@PostMapping("/modify/{notice_no}")
	//@PostMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody NoticeDto noticeDto) {
	    //noticeDto.setNotice_no(notice_no);
	    noticeService.modifyNotice(noticeDto);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	// 공지사항 삭제
	@PostMapping("/delete/{notice_no}")
	public ResponseEntity<?> delete(@PathVariable int notice_no){
		noticeService.deleteNotice(notice_no);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	

}
