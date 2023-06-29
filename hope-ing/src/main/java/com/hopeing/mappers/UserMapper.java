package com.hopeing.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hopeing.beans.vo.UserVO;

@Mapper
public interface UserMapper {
	
	// 회원가입(아이디 중복 체크)
	public int joinUserCheckId(@Param("user_id") String userId);
	
	// 회원가입
	public void joinUser(UserVO user);
}