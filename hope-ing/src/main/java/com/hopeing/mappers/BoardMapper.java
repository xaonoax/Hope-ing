package com.hopeing.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hopeing.beans.vo.BoardVO;

@Mapper
public interface BoardMapper {
	// 게시글 조회
	public BoardVO read(Long bno);
	
	// 게시글 등록
	public void register(BoardVO board);
	
	// 게시판 목록
	public List<BoardVO> getList();
}
