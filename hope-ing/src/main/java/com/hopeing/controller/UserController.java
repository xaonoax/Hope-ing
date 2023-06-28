package com.hopeing.controller;

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
@RequestMapping("/user/*")
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	// 회원가입 페이지 이동
	@GetMapping("join")
	public void join() {
		
	}
	
	// 회원가입 실행
	@PostMapping("signUp")
	public String signUp(UserVO user) {
		userService.joinUser(user);
		
		return "redirect:/hope-ing";
	}
}
