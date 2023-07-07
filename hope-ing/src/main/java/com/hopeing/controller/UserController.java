package com.hopeing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	// 로그인 페이지로 이동
	@GetMapping("login")
	public void loginUserGET() {
		
	}
	
	// 로그인
	@PostMapping("login")
	public String loginUserPOST(UserVO user) {
		
		userService.loginUser(user);
		
		return "redirect:/hope-ing";
	}
	
	// 아이디 중복 검사
	@PostMapping("idCheck")
	@ResponseBody
	public String idCheck(String user_id) {
		
		int result = userService.userIdCheck(user_id);
		
		if(result != 0) {
			return "fail";  // 중복된 아이디
		}
		else {
			return "success";
		}
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