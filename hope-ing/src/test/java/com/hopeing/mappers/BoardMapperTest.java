package com.hopeing.mappers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hopeing.beans.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	// 글 등록 테스트
//	@Test
	public void insert() {
		BoardVO board = new BoardVO();
		
		board.setBoard_title("title");
		board.setBoard_content("content");
		board.setBoard_writer_id("id");
		
		// boardMapper.register(board);
	}
	
	// 게시판 목록 테스트
//	@Test
	public void testGetList() {
		boardMapper.getList().forEach(board -> log.info("" + board));
	}
}
