package com.hopeing.service;

import com.hopeing.beans.vo.UserVO;

public interface UserService {
	// 아이디 중복체크
	public boolean checkDuplicateId(String user_id);
	// 회원가입
	public void joinUser(UserVO user);
}
