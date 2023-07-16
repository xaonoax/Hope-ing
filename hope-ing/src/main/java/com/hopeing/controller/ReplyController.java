package com.hopeing.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hopeing.beans.vo.ReplyVO;
import com.hopeing.beans.vo.UserVO;
import com.hopeing.service.ReplyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hope-ing/community/replies/*")
@Slf4j
public class ReplyController {
	private final ReplyService replyService;
	
	// 각 게시글별 댓글 조회
	@GetMapping("{reply_no}")
	public ReplyVO get(@PathVariable("reply_no") Long reply_no) {
		
		return replyService.read(reply_no);
	}
	
	// 댓글 등록
	@PostMapping(value="/new", consumes="application/json", produces="text/plain; charset=utf-8")
	public ResponseEntity<String> create(@RequestBody ReplyVO reply, HttpServletRequest request)
			throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		int insertCount = 0;
		
		reply.setReply_writer_id(user.getUser_id());
		insertCount = replyService.register(reply);
		
		if(insertCount == 1) {
			return new ResponseEntity<>(new String("댓글을 등록하였습니다.".getBytes(), "UTF-8"), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}