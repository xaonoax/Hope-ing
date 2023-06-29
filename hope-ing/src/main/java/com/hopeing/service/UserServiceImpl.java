package com.hopeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.UserVO;
import com.hopeing.mappers.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	// 회원가입
	@Override
	public void joinUser(UserVO user){
		// 아이디 중복 체크
		int duplicateIdCount = userMapper.joinUserCheckId(user.getUser_id());
		
		if (duplicateIdCount > 0) {
			throw new IllegalArgumentException("사용 중인 아이디입니다.");
		}
		
		userMapper.joinUser(user);
	}
}
