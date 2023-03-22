package com.team3.groupware.eunji.model;

import java.util.List;
import java.util.Map;

import com.team3.groupware.common.model.Criteria;


public interface BoardDAO {

	// 게시글 db 전달
	void board_write_insert(BoardVO boardVo);

	// 일반게시판 리스트 불러오기
	List<BoardVO> board_selectList(Criteria cri);

	// 게시글 디테일 데이터 불러오기
	Map<String, Object> board_detail(Map<String, Object> map);

	// 게시글 수정
	void board_modify(BoardVO boardVo);

	// 게시글 삭제
	void board_delete(BoardVO boardVo);

	// 게시글 검색
	List<Map<String, Object>> board_search(Map<String, Object> map);

	// 조회수 증가
	int board_view_plus(int board_num);
	
	// 공지게시판 리스트 불러오기  -김정치-
	List<BoardVO> board_selectNTList(Criteria cri);

	// 댓글 입력
	void board_comment_insert(Map<String, Object> comment);

	// 댓글 보이기
	List<Map<String, Object>> board_comment_select(int board_num);

	// 댓글 삭제
	void comment_delete(Map<String, Object> map);
	
	// 일반 게시판 총 개수 구하기
	int countTotal_normalList();

	// 공지 게시판 개수 구하기
	int countTotal_noticeList();
	
	// 자료실 게시판 개수 구하기
	int countTotal_boardDocuList();

	// 자료실 리스트 불러오기
	List<BoardVO> board_selectDList(Criteria cri);

	// 댓글 개수 보이기
	int board_comment_count(int board_num);



}
