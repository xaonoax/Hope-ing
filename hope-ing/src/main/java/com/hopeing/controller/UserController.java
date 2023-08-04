package com.hopeing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hopeing.beans.vo.UserVO;
import com.hopeing.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hope-ing/user/*")
@Slf4j
public class UserController {
	
	private final UserService userService;
	
	// 회원탈퇴
	@PostMapping("delete")
	public String deleteUser(HttpServletRequest request, RedirectAttributes rttr) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		// 회원 탈퇴 처리
		boolean result = userService.deleteUser(user.getUser_id());
		
		if (result) {
			session.invalidate(); // 세션 무효화
			rttr.addFlashAttribute("successMessage", "회원 탈퇴가 완료되었습니다.");
		}
		else {
			rttr.addFlashAttribute("errorMessage", "회원 탈퇴에 실패했습니다.");
		}
		
		return "redirect:/hope-ing";
	}
	
	// 마이페이지로 이동
	@GetMapping("mypage")
	public String myPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		if (user == null) {
			return "redirect:/hope-ing/user/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉션
		}
		else {
			model.addAttribute("user", user); // 회원 정보를 모델에 추가
			
			// 작성자 input 요소에 로그인 사용자의 닉네임 설정
			return "/hope-ing/user/myPage"; // 마이 페이지 템플릿으로 이동
	    }
	}
	
	// 마이페이지(개인 정보 수정)
	@PostMapping("mypage")
	public RedirectView updateMyPage(UserVO user, HttpServletRequest request, RedirectAttributes rttr) {
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("user");
		
		if (loginUser == null) {
			return new RedirectView("/hope-ing/user/login"); // 로그인되지 않은 경우 로그인 페이지로 리다이렉션
		}
		else {
			user.setUser_id(loginUser.getUser_id()); // 로그인된 사용자의 ID로 설정
			
			int result = userService.updateUser(user);
			
			if (result > 0) {
				loginUser.setUser_id(user.getUser_id());
				loginUser.setUser_pw(user.getUser_pw());
				loginUser.setUser_nickname(user.getUser_nickname());
				loginUser.setUser_email(user.getUser_email());
				loginUser.setUser_phone_num(user.getUser_phone_num());
				
				session.setAttribute("user", loginUser);
				rttr.addFlashAttribute("successMessage", "정보 수정이 완료되었습니다.");
			} 
			else {
				rttr.addFlashAttribute("errorMessage", "정보 수정에 실패했습니다.");
			}

	        return new RedirectView("/hope-ing/user/mypage?id=" + loginUser.getUser_id());
	    }
	}
	
	// 로그아웃
	@GetMapping("logout")
	public String logoutUserGET(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 무효화
		
		return "redirect:/hope-ing";
	}
	
	// 로그인 페이지로 이동
	@GetMapping("login")
	public void loginUserGET() {
		
	}
	
	// 로그인
	@PostMapping("login")
	public String loginUserPOST(UserVO user, HttpServletRequest request, RedirectAttributes rttr) {
		
		HttpSession session = request.getSession();
		UserVO loginUser = userService.loginUser(user);
		
		if(loginUser == null) {  // 아이디, 비밀번호 잘못 입력
			int result = 0;
			rttr.addFlashAttribute("result", result);
			
			return "redirect:/hope-ing/user/login";
		}
		
		session.setAttribute("user", loginUser);  // 로그인 성공
		
		int result = 1;
		rttr.addFlashAttribute("result", result);
		
		return "redirect:/hope-ing";
	}
	
	// 아이디 중복 검사
	@PostMapping("userIdCheck")
	@ResponseBody
	public String userIdCheck(String user_id) {
		
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