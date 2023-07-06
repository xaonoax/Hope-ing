package com.hopeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.UserVO;
import com.hopeing.mappers.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	// 아이디 중복 체크(회원가입)
	@Override
	public int joinCheckUserId(String user_id) {
		return userMapper.joinCheckUserId(user_id);
	}
	
	// 회원가입
	@Override
	public void joinUser(UserVO user){
		userMapper.joinUser(user);
	}
}
