package com.hopeing.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.LikeVO;

@Mapper
public interface LikeMapper {
	// 좋아요 번호, 좋아요 id로 정보 조회
	public LikeVO read(Long like_no, String like_id);
	
	// 게시글의 좋아요 수
	public Long boardLikeCount(Long like_bno);
	
	// 게시글 좋아요 업데이트
	public void updateBoardLikeCount(BoardVO board);
	
	// 좋아요 업데이트
	public void updateLikeCount(LikeVO like);
	
	// 게시글 좋아요 상태
	public void toggle(Long board_no, String user_id);
	
	// 좋아요 취소
	public void delete(LikeVO like);
	
	// 좋아요
	public void insert(LikeVO like);
}
