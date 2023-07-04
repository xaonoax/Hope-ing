package com.hopeing.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.hopeing.beans.vo.UserVO;

@Mapper
public interface UserMapper {
	// 아이디 중복 체크(회원가입)
	public UserVO checkDuplicateId(String user_id);
	
	// 회원가입
	public void joinUser(UserVO user);
}
