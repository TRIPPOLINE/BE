package com.ssafy.trip.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

	private final int DEFAULT_PAGE_SIZE = 10; //TODO : 리팩터링
	
	@Autowired
	private NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	// 공지사항 목록

//	@GetMapping("/list")
//	public ResponseEntity<?> listNotice(@RequestParam Map<String, String> map){
//		List<NoticeDto> list = noticeService.listNotice(map);
//		if(list==null || list.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}

	// 오프셋 기반 페이지네이션
	// pgno: 페이지 번호, sizePerPage: 페이지당 항목 수, key: 검색키, word: 검색어
	@GetMapping("/list")
	public ResponseEntity<?> listNotice(@RequestParam(defaultValue = "1") int pgno,
										@RequestParam(defaultValue = "10") int sizePerPage,
										@RequestParam(required = false) String key,
										@RequestParam(required = false) String word) {
		Map<String, Object> map = new HashMap<>();
		map.put("pgno", pgno);
		map.put("sizePerPage", sizePerPage);
		map.put("key", key);
		map.put("word", word);

		List<NoticeDto> list = noticeService.listNotice(map);
		int totalCount = noticeService.getTotalNoticeCount(map);  // 공지사항 총 글 개수

		if (list == null || list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("notices", list);
		responseMap.put("totalCount", totalCount);
		responseMap.put("currentPage", pgno);
		responseMap.put("sizePerPage", sizePerPage);

		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	// 공지사항 글 작성
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody NoticeDto noticeDto, HttpSession session) {
		//TODO 관리자 인증하기 추가
	    noticeService.writeNotice(noticeDto);
	    return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	// 공지사항 글 수정 페이지
	@GetMapping("/modify/{notice_no}")
	public ResponseEntity<?> getModify(@PathVariable int notice_no) {
	    NoticeDto noticeDto = noticeService.selectNotice(notice_no);
	    if(noticeDto==null) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(noticeDto, HttpStatus.OK);
	}
	
	// 공지사항 글 수정
	@PostMapping("/modify/{notice_no}")
	//@PostMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody NoticeDto noticeDto) {
	    //noticeDto.setNotice_no(notice_no);
	    noticeService.modifyNotice(noticeDto);
	    return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	// 공지사항 글 삭제
	@PostMapping("/delete/{notice_no}")
	public ResponseEntity<?> delete(@PathVariable int notice_no){
		noticeService.deleteNotice(notice_no);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	

}
