package com.hopeing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.ReplyVO;
import com.hopeing.mappers.ReplyMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyMapper replyMapper;
	
	// 각 게시글별 댓글 조회
	public ReplyVO read(Long reply_no) {
		return replyMapper.read(reply_no);
	}
	
	// 댓글 등록
	public int register(ReplyVO reply) {
		return replyMapper.register(reply);
	}
	
	// 댓글 목록
	public List<ReplyVO> getList(Long reply_bno) {
		return replyMapper.getList(reply_bno);
	}
}
