package com.hopeing.service;

import java.util.List;

import com.hopeing.beans.vo.ReplyVO;

public interface ReplyService {
	// 각 게시글별 댓글 조회
	public ReplyVO read(Long reply_no);
	
	// 댓글 등록
	public int register(ReplyVO reply);
	
	// 댓글 목록
	public List<ReplyVO> getList(Long reply_bno);
}