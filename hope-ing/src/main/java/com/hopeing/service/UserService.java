package com.hopeing.service;

import com.hopeing.beans.vo.UserVO;

public interface UserService {
	// 닉네임 중복 체크(회원가입)
	public boolean joinCheckDuplicateNickname(String nickname);
	
	// 아이디 중복 체크(회원가입)
	public boolean joinCheckDuplicateId(String user_id);
	
	// 회원가입
	public void joinUser(UserVO user);
}
