package com.hopeing.mappers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hopeing.beans.vo.LikeVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class LikeMapperTest {
	@Autowired
	private LikeMapper likeMapper;
	
	// 좋아요 기능 테스트
//	@Test
	public void testToggle() {
		// 좋아요가 0일 때 누르기
		Long like_no = 0L;
		Long like_bno = 1L;
		String like_id = "aaaa77";
		
		LikeVO like = likeMapper.read(like_bno, like_id);
		
		if (like == null) {
			like = new LikeVO();
			like.setLike_no(like_no);
			like.setLike_bno(like_bno);
			like.setLike_id(like_id);
			like.setLike_count(1L);
			
			likeMapper.insert(like);
		}
		else {
			if (like.getLike_count() == 0L) {
				like.setLike_count(1L);
				
				likeMapper.updateLikeCount(like);
			}
			else if (like.getLike_count() == 1L) {
				likeMapper.delete(like);
			}
		}
	}
}
