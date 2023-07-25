package com.hopeing.service;

import com.hopeing.beans.vo.UserVO;

public interface UserService {
	// 아이디로 사용자 정보 조회
	public UserVO userId(String user_id);
	
	// 회원탈퇴
	public boolean deleteUser(String user_id);
	
	// 정보 수정
	public int updateUser(UserVO user);
	
	// 로그인
	public UserVO loginUser(UserVO user);
	
	// 아이디 중복 체크(회원가입)
	public int userIdCheck(String user_id);
    
	// 회원가입
	public void joinUser(UserVO user);
}
