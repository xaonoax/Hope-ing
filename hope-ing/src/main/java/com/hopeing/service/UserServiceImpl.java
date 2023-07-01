package com.hopeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.UserVO;
import com.hopeing.mappers.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	// 닉네임 중복 체크(회원가입)
	public boolean joinCheckDuplicateNickname(String user_nickname) {
		UserVO user = userMapper.joinCheckDuplicateId(user_nickname);
		return user != null;
	}
	
	// 아이디 중복 체크(회원가입)
	@Override
	public boolean joinCheckDuplicateId(String user_id) {
		UserVO user = userMapper.joinCheckDuplicateId(user_id);
		return user != null;
	}
	// 회원가입
	@Override
	public void joinUser(UserVO user){
		userMapper.joinUser(user);
	}
}
