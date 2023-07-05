package com.hopeing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hopeing.beans.vo.UserVO;
import com.hopeing.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hope-ing/user/*")
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("checkDuplicateUserId")
	public ResponseEntity<Boolean> checkDuplicateUserId(@RequestParam("user_id") String user_id) {
		// 아이디 중복 체크
		boolean isDuplicate = userService.checkDuplicateUserId(user_id);
		return new ResponseEntity<>(isDuplicate, HttpStatus.OK);
	}
	
	// 회원가입 페이지 이동
	@GetMapping("join")
	public void joinUserGET() {
		
	}
	
	// 회원가입 실행
	@PostMapping("join")
	public String joinUserPOST(UserVO user) {
		userService.joinUser(user);
		
		return "redirect:/hope-ing/user/login";
	}
}