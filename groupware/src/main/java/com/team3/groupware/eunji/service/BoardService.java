package com.team3.groupware.eunji.service;

import java.util.List;
import java.util.Map;

import com.team3.groupware.eunji.model.BoardVO;

public interface BoardService {

	// 게시글 작성
	void board_write_insert(BoardVO boardVo);

	// 게시글 리스트 불러오기
	List<BoardVO> board_selectList(BoardVO boardVo);

	// 게시글 디테일 보기
	Map<String,Object> board_detail(Map<String, Object> map);

	// 게시글 수정
	void board_modify(BoardVO boardVo);

	// 게시글 삭제
	void board_delete(BoardVO boardVo);

	// 게시글 검색
	List<Map<String, Object>> board_search(Map<String, Object> map);

	// 조회수 증가
	int board_view_plus(int board_num);


	// 공지게시글 리스트 불러오기 -정치-
	List<BoardVO> board_selectNTList(BoardVO boardVo);

	// 댓글 입력
	void board_comment_insert(Map<String, Object> comment);

	// 댓글 보이기
	List<Map<String, Object>> board_comment_select(int board_num);

	// 댓글 삭제
	void comment_delete(Map<String, Object> map);

	// 댓글 수정
	void comment_modify(Map<String, Object> map);






}
