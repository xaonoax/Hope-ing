package com.hopeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hopeing.beans.vo.UserVO;
import com.hopeing.mappers.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper userMapper;
	
	// 아이디로 사용자 정보 조회
	public UserVO userId(String user_id) {
		return userMapper.userId(user_id);
	}
	
	// 회원탈퇴
	public boolean deleteUser(String user_id) {
		int result = userMapper.deleteUser(user_id);
		return result > 0;
	}
	
	// 정보 수정
	@Override
	public int updateUser(UserVO user) {
		return userMapper.updateUser(user);
	}
	
	// 로그인
	@Override
	public UserVO loginUser(UserVO user) {
		return userMapper.loginUser(user);
	}
	
	// 아이디 중복 체크(회원가입)
	@Override
	public int userIdCheck(String user_id) {
		return userMapper.userIdCheck(user_id);
	}
	
	// 회원가입
	@Override
	public void joinUser(UserVO user){
		userMapper.joinUser(user);
	}
}
