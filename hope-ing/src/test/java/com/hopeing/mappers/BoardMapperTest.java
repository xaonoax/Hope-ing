package com.hopeing.mappers;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.Criteria;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class BoardMapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	// 게시글 목록 페이징
	@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria();
		
		cri.setAmount(1);
		cri.setPageNum(0);
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board.getBoard_no() + ""));
	}
	
	// 글 등록 테스트
//	@Test
	public void register() {
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
