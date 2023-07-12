package com.hopeing.beans.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardVO {
	private Long board_no;					// 게시글 번호
	private String board_title;				// 게시글 제목
	private String board_content;			// 게시글 내용
	private String board_writer_nickname;	// 작성자 닉네임
	private String board_views;				// 게시글 조회수
	private String board_like_count;		// 게시글 좋아요 수
	private Date board_regdate;				// 게시글 작성일
	private Date board_updatedate;			// 게시글 수정일
}
