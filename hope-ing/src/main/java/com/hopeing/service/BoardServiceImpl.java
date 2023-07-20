package com.hopeing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.Criteria;
import com.hopeing.mappers.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	// 게시글 갯수
	@Override
	public int getTotal(Criteria cri) {
		return boardMapper.getTotal(cri);
	}
	
	// 게시글 목록 페이징
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}
	
	// 게시글 삭제
	@Override
	public int delete(Long board_no) {
		return boardMapper.delete(board_no);
	}
	
	// 게시글 수정
	@Override
	public boolean update(BoardVO board) {
		int affectedRows = boardMapper.update(board);
		
		return affectedRows > 0;
	}
	
	// 게시글 조회수
	@Override
	public boolean viewsUpdate(Long board_no) {
		return boardMapper.viewsUpdate(board_no);
	}
	
	// 게시글 조회
	@Override
	public BoardVO read(Long board_no) {
		return boardMapper.read(board_no);
	}
	
	// 게시글 등록
	@Override
	public void register(BoardVO board) {
		boardMapper.register(board);
		
	}
	
	// 게시글 목록
	@Override
	public List<BoardVO> getList() {
		return boardMapper.getList();
	}
}
