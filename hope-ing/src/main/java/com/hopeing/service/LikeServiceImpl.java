package com.hopeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.LikeVO;
import com.hopeing.beans.vo.UserVO;
import com.hopeing.mappers.BoardMapper;
import com.hopeing.mappers.LikeMapper;
import com.hopeing.mappers.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	private LikeMapper likeMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	// 게시글의 좋아요 수
	@Override
	public Long boardLikeCount(Long like_bno) {
		return likeMapper.boardLikeCount(like_bno);
	}
	
	// 게시글 좋아요 업데이트
	@Override
	public void updateBoardLikeCount(BoardVO board) {
		likeMapper.updateBoardLikeCount(board);
	}
	
	// 좋아요 업데이트
	@Override
	public void updateLikeCount(LikeVO like) {
		likeMapper.updateLikeCount(like);
	}
	
	@Override
	// 게시글 좋아요 상태
	public void toggle(Long board_no, String user_id) {
		BoardVO board = boardMapper.getBoardNo(board_no);
    	UserVO user = userMapper.userId(user_id);
    	
    	LikeVO like = likeMapper.read(board_no, user_id);
    	
		if (like == null) {  // 좋아요를 누르지 않았을 때
			like = new LikeVO();
			like.setLike_bno(board_no);
			like.setLike_id(user_id);
			like.setLike_count(1L);
			
			likeMapper.insert(like);
			
			board.setBoard_like_count(board.getBoard_like_count() + 1L);
			
			likeMapper.updateBoardLikeCount(board);
		}
		else {  // 좋아요가 눌러진 상태
			if (like.getLike_count() == 0L) {  // 좋아요를 취소하고 다시 좋아요를 눌렀을 때
				like.setLike_count(1L);  // 좋아요 수 1로 변경
				
				likeMapper.updateLikeCount(like);
				
				board.setBoard_like_count(board.getBoard_like_count() + 1L);  // 게시글의 좋아요 수 1 증가
				likeMapper.updateBoardLikeCount(board);
				
			}
			else if (like.getLike_count() == 1L) {  // 이미 좋아요를 누른 상태에서 좋아요를 취소했을 때
				likeMapper.delete(like);
				
				board.setBoard_like_count(board.getBoard_like_count() - 1L);  // 좋아요 수 -1로 변경
				likeMapper.updateBoardLikeCount(board);
			}
		}
	}
	
	// 좋아요 취소
	@Override
	public void delete(LikeVO like) {
		likeMapper.delete(like);
	}
	
	// 좋아요
	@Override
	public void insert(LikeVO like) {
		likeMapper.insert(like);
	}
}
