package com.hopeing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	// 회원가입 페이지 이동
	@GetMapping("join")
	public void joinUserGET() {
	}
	
	// 회원가입 실행
	@PostMapping("join")
	public ResponseEntity<String> joinUserPOST(UserVO user) {
		boolean duplicateId = userService.joinCheckDuplicateId(user.getUser_id());
		boolean duplicateNickname = userService.joinCheckDuplicateNickname(user.getUser_nickname());
		
		if (duplicateId) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디입니다.");
		}
		else if (duplicateNickname) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 닉네임입니다.");
		}
		else {
			userService.joinUser(user);
			return ResponseEntity.ok("회원가입이 완료되었습니다.");
		}
	}
}