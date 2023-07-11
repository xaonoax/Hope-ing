package com.hopeing.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.hopeing.beans.vo.UserVO;

@Mapper
public interface UserMapper {
	// 회원탈퇴
	public int deleteUser(String user_id);
	
	// 정보 수정
	public int updateUser(UserVO user);
	
	// 로그인
	public UserVO loginUser(UserVO user);
	
	// 아이디 중복 체크(회원가입)
	public int userIdCheck(String user_id);
    
	// 회원가입
	public void joinUser(UserVO user);
}
