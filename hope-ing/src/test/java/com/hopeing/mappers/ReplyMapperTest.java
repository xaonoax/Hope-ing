package com.hopeing.mappers;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hopeing.beans.vo.ReplyVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class ReplyMapperTest {
	@Autowired
	private ReplyMapper replyMapper;
	
	// 댓글 등록 테스트
	@Test
	public void testGetList() {
		// 1개의 게시글에 댓글 2개씩 달아보기
		IntStream.rangeClosed(1, 2).forEach(i -> {
			ReplyVO reply = new ReplyVO();
			
			reply.setReply_bno(1L);
			reply.setReply_content("댓글 자동생성" + i);
			reply.setReply_writer_id("test_user");
			
			replyMapper.register(reply);
		});
	}
}
