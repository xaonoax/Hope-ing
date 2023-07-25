package com.hopeing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hopeing.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hope-ing/community/likes/*")
@Slf4j
public class LikeController {
	@Autowired
	private LikeService likeService;
	
	@PostMapping("/{board_no}")
	public ResponseEntity<String> togglePOST(@PathVariable("board_no") Long board_no,
			@RequestParam("user_id") String user_id,
			RedirectAttributes redirectAttributes) {
		
		// 게시물의 좋아요 상태를 변경
		likeService.toggle(board_no, user_id);
		
		redirectAttributes.addAttribute("board_no", board_no);
		
		String redirectUrl = "/hope-ing/community/read?board_no=" + board_no;
		
		return ResponseEntity.ok().body(redirectUrl);
	}
}