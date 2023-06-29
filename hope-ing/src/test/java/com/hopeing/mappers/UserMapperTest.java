package com.hopeing.mappers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hopeing.beans.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class UserMapperTest {
	@Autowired
	private UserMapper userMapper;
	
	// 아이디 중복 테스트 메서드
//	@Test
	public void joinUserCheckIdTest() {
		String userId = "test1";
		int duplicateIdCount = userMapper.joinUserCheckId(userId);
		
		if (duplicateIdCount > 0) {
			log.info("사용 중인 아이디입니다.");
		}
		else {
			log.info("사용 가능한 아이디입니다.");
		}
		
	}
	
	// 회원가입 테스트 메서드
//	@Test
	public void joinTest() {
		UserVO user = new UserVO();
		
		user.setUser_id("test1");
		user.setUser_pw("test2");
		user.setUser_name("test3");
		user.setUser_nickname("test4");
		user.setUser_email("test5");
		user.setUser_phone_num("test6");
		
		userMapper.joinUser(user);
	}
}