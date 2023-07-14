package com.hopeing.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.UserVO;
import com.hopeing.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hope-ing/community/*")
@Slf4j
public class BoardController {
	private final BoardService boardService;
	
	// 게시글 조회
	@GetMapping("read")
	public String boardReadGET(@RequestParam("board_no") Long board_bno, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		BoardVO board = boardService.read(board_bno);
		String writer = board.getBoard_writer_nickname();
		
		// 로그인한 사용자가 글 작성자인 경우에만 isWriter를 true로 설정
		boolean Writer = (user != null && user.getUser_id().equals(writer));
		model.addAttribute("board", board);
		model.addAttribute("isWriter", Writer);
		
		return "/hope-ing/community/read";
	}
	
	// 글 등록 페이지 이동
	@GetMapping("register")
	public void boardRegisterGET() {
		log.info("글 작성----------");
	}
	
	// 글 등록
	@PostMapping("register")
	public RedirectView boardRegisterPOST(BoardVO board, HttpServletRequest request,
			RedirectAttributes rttr,
			MultipartFile file)
	throws Exception{
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		
		if (user == null) {
			return new RedirectView("/hope-ing/user/login"); // 로그인되지 않은 경우 로그인 페이지로 리다이렉션
		}
		else {
			// 작성자 닉네임 업데이트
	        board.setBoard_writer_nickname(user.getUser_id());
			// 글 등록
			boardService.register(board);
			// 글 등록 후 전체 게시판으로 리다이렉션
			return new RedirectView("/hope-ing/community/list");
		}
	}
	
	// 전체게시판 목록 이동
	@GetMapping("list")
	public String listGET(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
	    
	    if (user == null) {
	    	return "redirect:/user/login"; // 로그인되지 않은 경우 로그인 페이지로 리다이렉션
	    }
	    else {
	    	List<BoardVO> getList = boardService.getList(); // 게시물 목록 가져오기
	    	model.addAttribute("list", getList); // 목록을 모델에 추가
	    	model.addAttribute("user", user); // 회원 정보를 모델에 추가
	    	
	    	return "/hope-ing/community/list";
	    }
	}
}
