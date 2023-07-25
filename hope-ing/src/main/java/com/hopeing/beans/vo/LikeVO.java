package com.hopeing.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class LikeVO {
	private UserVO user;  // UserVO 타입의 필드 추가
	private BoardVO board;  // BoardVO 타입의 필드 추가
	
	private Long like_no;		// 좋아요 번호
	private Long like_bno;		// 좋아요한 게시글 번호
	private String like_id;		// 좋아요한 사용자
	private Long like_count;	// 좋아요 수
}
