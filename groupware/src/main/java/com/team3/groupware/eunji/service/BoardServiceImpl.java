package com.team3.groupware.eunji.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.team3.groupware.common.model.Criteria;
import com.team3.groupware.eunji.model.BoardDAO;
import com.team3.groupware.eunji.model.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;

	// 게시글 작성
	@Override
	public void board_write_insert(BoardVO boardVo) {
		this.boardDao.board_write_insert(boardVo);
		
	}

	// 게시글 리스트 불러오기
	@Override
	public List<BoardVO> board_selectList(Criteria cri) {
		return boardDao.board_selectList(cri);
		
	}

	// 게시판 디테일 페이지
	@Override
	public Map<String, Object> board_detail(Map<String, Object> map) {
		return boardDao.board_detail(map);
	}

	// 게시글 수정
	@Override
	public void board_modify(BoardVO boardVo) {
		this.boardDao.board_modify(boardVo);
	}

	// 게시글 삭제
	@Override
	public void board_delete(BoardVO boardVo) {
		this.boardDao.board_delete(boardVo);
		
	}

	// 게시글 검색
	@Override
	public List<Map<String, Object>> board_search(Map<String, Object> map) {
		return boardDao.board_search(map);
	}

	// 조회수 증가
	@Override
	public int board_view_plus(int board_num) {
		return boardDao.board_view_plus(board_num);
	}

	// 댓글 입력
	@Override
	public void board_comment_insert(Map<String, Object> comment) {
		this.boardDao.board_comment_insert(comment);
		
	}

	// 댓글 보이기
	@Override
	public List<Map<String, Object>> board_comment_select(int board_num) {
		return boardDao.board_comment_select(board_num);
	}

	// 댓글 삭제
	@Override
	public void comment_delete(Map<String, Object> map) {
		this.boardDao.comment_delete(map);
		
	}

	
	// 공지게시글 리스트 불러오기 -정치-
	@Override
	public List<BoardVO> board_selectNTList(Criteria cri) {
		return boardDao.board_selectNTList(cri);
	}

	// 일반 게시판 총 개수 구하기
	@Override
	public int countTotal_normalList() {
		return boardDao.countTotal_normalList();
	}

	// 공지게시판 개수 구하기
	@Override
	public int countTotal_noticeList() {
		return boardDao.countTotal_noticeList();
	}

	// 자료실 게시판 개수 구하기
	@Override
	public int countTotal_boardDocuList() {
		return boardDao.countTotal_boardDocuList();
	}

	// 자료실 리스트 불러오기
	@Override
	public List<BoardVO> board_selectDList(Criteria cri) {
		return boardDao.board_selectDList(cri);
	}

	// 댓글 개수 보이기
	@Override
	public int board_comment_count(int board_num) {
		return boardDao.board_comment_count(board_num);
	}




}
