package com.hopeing.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hopeing.beans.vo.BoardVO;
import com.hopeing.beans.vo.Criteria;
import com.hopeing.mappers.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	// 번호별 게시글 조회
	@Override
	public BoardVO getBoardNo(Long board_no) {
		return boardMapper.getBoardNo(board_no);
	}
	
	// 게시글 갯수
	@Override
	public int getTotal(Criteria cri) {
		return boardMapper.getTotal(cri);
	}
	
	// 게시글 목록 페이징
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}
	
	// 게시글 삭제
	@Override
	public int delete(Long board_no) {
		return boardMapper.delete(board_no);
	}
	
	// 게시글 수정
	@Override
	public boolean update(BoardVO board, MultipartFile file) throws Exception{
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/updatefiles";
		
		UUID uuid = UUID.randomUUID();
		
		String file_name = uuid + "_" + file.getOriginalFilename();
		File saveFile = new File(projectPath, file_name);
		
		board.setBoard_file_name(file_name);
		board.setBoard_file_path("/updatefiles/" + file_name);
		
		file.transferTo(saveFile);
		
		int affectedRows = boardMapper.update(board, file);
		
		return affectedRows > 0;
	}
	
	// 게시글 조회수
	@Override
	public boolean viewsUpdate(Long board_no) {
		return boardMapper.viewsUpdate(board_no);
	}
	
	// 게시글 조회
	@Override
	public BoardVO read(Long board_no) {
		return boardMapper.read(board_no);
	}
	
	// 게시글 등록
	@Override
	public void register(BoardVO board, MultipartFile file) throws Exception{
		String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";
		
		UUID uuid = UUID.randomUUID();
		
		String file_name = uuid + "_" + file.getOriginalFilename();
		File saveFile = new File(projectPath, file_name);
		
		board.setBoard_file_name(file_name);
		board.setBoard_file_path("/files/" + file_name);
		
		file.transferTo(saveFile);
		
		boardMapper.register(board, file);
	}
	
	// 게시글 목록
	@Override
	public List<BoardVO> getList(Criteria cri) {
		return boardMapper.getList(cri);
	}
}
