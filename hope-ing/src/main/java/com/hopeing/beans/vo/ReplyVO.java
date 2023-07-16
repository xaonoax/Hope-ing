package com.hopeing.beans.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReplyVO {
	private Long reply_no;					// 댓글 번호
	private Long reply_bno;					// 게시글 번호
	private String reply_content;			// 댓글 내용
	private String reply_writer_id;			// 댓글 작성자
	private Date reply_regdate;				// 댓글 작성일
	private Date reply_updatedate;			// 댓글 수정일
}
