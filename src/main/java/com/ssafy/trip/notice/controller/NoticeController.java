package com.ssafy.trip.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.global.util.PageResult;
import com.ssafy.trip.review.dto.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.notice.dto.NoticeDto;
import com.ssafy.trip.notice.service.NoticeService;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("/list")
	public ResponseEntity<PageResult<NoticeDto>> listNotice(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size) {

		PageResult<NoticeDto> result = noticeService.listNotice(page, size);

		if (result.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<PageResult<NoticeDto>> searchNotice(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false) String key,
			@RequestParam(required = false) String word) {

		PageResult<NoticeDto> result = noticeService.searchNotice(page, size, key, word);

		if (result.getContent().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody NoticeDto noticeDto) {
		noticeService.writeNotice(noticeDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/modify/{noticeNo}")
	public ResponseEntity<?> getModify(@PathVariable int noticeNo) {
		NoticeDto noticeDto = noticeService.selectNotice(noticeNo);
		if(noticeDto == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(noticeDto, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/modify/{noticeNo}")
	public ResponseEntity<?> modify(@PathVariable int noticeNo, @RequestBody NoticeDto noticeDto) {
		noticeDto.setNoticeNo(noticeNo);
		noticeService.modifyNotice(noticeDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/delete/{noticeNo}")
	public ResponseEntity<?> delete(@PathVariable int noticeNo) {
		noticeService.deleteNotice(noticeNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}