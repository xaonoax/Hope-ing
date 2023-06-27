package com.hopeing.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.hopeing.beans.vo.UserVO;

@Mapper
public interface UserMapper {
	// 회원가입
	public void signUpUser(UserVO user);
}