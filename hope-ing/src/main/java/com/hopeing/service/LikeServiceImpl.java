package com.hopeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.LikeVO;
import com.hopeing.mappers.LikeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LikeServiceImpl implements LikeService {
	
	@Autowired
	private LikeMapper likeMapper;
	
    @Override
    public Long likeCount(Long board_no) {
    	return likeMapper.likeCount(board_no);
    }
    
    @Override
    public void updateBoardLikeCount(BoardVO board) {
    	likeMapper.updateBoardLikeCount(board);
    }
	
    @Override
    public void updateLikeCount(LikeVO like) {
    	likeMapper.updateLikeCount(like);
    }
    
    @Override
    public void toggle(Long like_no, Long like_bno, String like_id) {
    	BoardVO board = new BoardVO();
    	
    	LikeVO like = likeMapper.read(like_no, like_bno, like_id);
    	
    	if (like == null) {  // 좋아요를 누르지 않았을 때
    		like = new LikeVO();
    		like.setLike_no(like_no);
    		like.setLike_bno(like_bno);
    		like.setLike_id(like_id);
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
    			
    			board.setBoard_like_count(board.getBoard_like_count() - 1L);  // 게시글의 좋아요 수 1 증가
    			likeMapper.updateBoardLikeCount(board);
    		}
    	}
    }

    @Override
    public void delete(LikeVO like) {
    	likeMapper.delete(like);
    }

    @Override
    public void insert(LikeVO like) {
        likeMapper.insert(like);
    }
}
