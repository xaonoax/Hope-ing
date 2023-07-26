package com.hopeing.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.Criteria;

public interface BoardService {
	// 번호별 게시글 조회
	public BoardVO getBoardNo(Long board_no);
	
	// 게시글 갯수
	public int getTotal(Criteria cri);
	
	// 게시글 목록 페이징
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 게시글 삭제
	public int delete(Long board_no);
	
	// 게시글 수정
	public boolean update(BoardVO board, MultipartFile file) throws Exception;
	
	// 게시글 조회수
	public boolean viewsUpdate(Long board_no);
	
	// 게시글 조회
	public BoardVO read(Long board_no);
	
	// 글 등록
	public void register(BoardVO board, MultipartFile file) throws Exception;
	
	// 게시판 목록
	public List<BoardVO> getList(Criteria cri);
}
