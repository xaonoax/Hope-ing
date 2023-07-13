package com.hopeing.mappers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	// 게시판 목록 테스트
//	@Test
	public void testGetList() {
		boardMapper.getList().forEach(board -> log.info("" + board));
	}
}
