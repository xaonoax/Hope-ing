package com.hopeing.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hopeing.beans.vo.Criteria;
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
	
	// 게시글 댓글 전체 조회
	@GetMapping("pages/{reply_bno}/{page}")
	public List<ReplyVO> getList(@PathVariable("reply_bno") Long reply_bno, @PathVariable("page") int page) {
		Criteria cri = new Criteria(page, 10);
		
		return replyService.getList(cri, reply_bno);
	}
	
	// 댓글 삭제
	@DeleteMapping(value="{reply_no}", produces="text/plain; charset=utf-8")
	public String delete(@PathVariable("reply_no") Long reply_no) {
		
		return replyService.delete(reply_no) == 1 ? "댓글을 삭제하였습니다." : "댓글 삭제에 실패하였습니다.";
	}
	
	// 댓글 수정
	@RequestMapping(method= {RequestMethod.PATCH},
			value= {"/{reply_no}", "/{reply_no}/{reply_writer_id}"},
			consumes="application/json",
			produces="text/plain; charset=utf-8")
	public ResponseEntity<String> update(
			@RequestBody ReplyVO replyVO,
			@PathVariable(value = "reply_writer_id", required = false) String reply_writer_id,
			@PathVariable("reply_no") Long reply_no,
			HttpServletRequest request)
	throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
	    UserVO user = (UserVO) session.getAttribute("user");
	    
	    replyVO.setReply_writer_id(user.getUser_id());
		replyVO.setReply_no(reply_no);
		
		int replyCount = 0;
		log.info("modify ------------------------------------> " + reply_no);
		log.info("modify : " + replyVO);
		
		
		if(replyVO.getReply_writer_id() == null) {
			replyVO.setReply_writer_id(Optional.ofNullable(reply_writer_id).orElse("anonymous"));
		}
		replyCount = replyService.update(replyVO);
		
		if(replyCount == 1) {
			return new ResponseEntity<>(new String("댓글을 수정하였습니다.".getBytes(), "UTF-8"), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 각 게시글별 댓글 조회
	@GetMapping("{reply_no}")
	public ReplyVO read(@PathVariable("reply_no") Long reply_no) {
		
		return replyService.read(reply_no);
	}
	
	// 댓글 등록
	@PostMapping(value="/new", consumes="application/json", produces="text/plain; charset=utf-8")
	public ResponseEntity<String> register(@RequestBody ReplyVO reply, HttpServletRequest request)
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