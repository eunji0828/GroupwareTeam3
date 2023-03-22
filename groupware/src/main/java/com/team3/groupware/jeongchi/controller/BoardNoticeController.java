package com.team3.groupware.jeongchi.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.team3.groupware.common.model.Criteria;
import com.team3.groupware.common.model.PageMaker;
import com.team3.groupware.eunji.model.BoardVO;
import com.team3.groupware.eunji.service.BoardService;

@RestController
public class BoardNoticeController {
	
	@Inject
	BoardService boardService;
	
	// 공지 게시판
	@GetMapping("/board_notice")
	public ModelAndView boardNotice(BoardVO boardVo, HttpServletRequest request, Criteria cri) {
		
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		int emp_num = 0;
		
		
		if(session.getAttribute("emp_num") != null) {
			String change = String.valueOf(session.getAttribute("emp_num"));
			
			// 페이징
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(boardService.countTotal_noticeList());
			
			List<BoardVO> boardNoticeList = boardService.board_selectNTList(cri);
			mv.addObject("boardNoticeList", boardNoticeList);
			mv.addObject("pageMaker", pageMaker);
		}
		mv.setViewName("/jeongchi/board/board_notice");
		return mv;
	}

}
