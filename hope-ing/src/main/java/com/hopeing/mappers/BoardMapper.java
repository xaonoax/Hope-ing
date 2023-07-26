package com.hopeing.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.Criteria;

@Mapper
public interface BoardMapper {
	// 번호별 게시글 조회
	public BoardVO getBoardNo(Long board_no);
	
	// 게시글 갯수
	public int getTotal(Criteria cri);
	
	// 게시글 목록 페이징
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 게시글 삭제
	public int delete(Long board_no);
	
	// 게시글 수정
	public int update(@Param("board") BoardVO board, @Param("file") MultipartFile file) throws Exception;
	
	// 게시글 조회수 업데이트
	public boolean viewsUpdate(Long board_bno);
	
	// 게시글 조회
	public BoardVO read(Long board_no);
	
	// 게시글 등록
	public void register(@Param("board") BoardVO board, @Param("file") MultipartFile file) throws Exception;
	
	// 게시판 목록
	public List<BoardVO> getList(Criteria cri);
}
