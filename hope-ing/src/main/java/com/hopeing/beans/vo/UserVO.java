package com.hopeing.beans.vo;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserVO {
	private Long user_no;			// 유저 고유번호
	private String user_id;			// 유저 아이디
	private String user_pw;			//유저 비밀번호
	private String user_name;		// 유저 이름
	private String user_nickname;  	// 유저 닉네임
	private String user_email;  	// 유저 이메일
	private String user_phone_num;	// 유저 전화번호
	private String user_profile;	// 유저 프로필
	private Date user_regdate;		// 유저 생성일
	private Date user_updatedate;	// 유저 수정일
}
