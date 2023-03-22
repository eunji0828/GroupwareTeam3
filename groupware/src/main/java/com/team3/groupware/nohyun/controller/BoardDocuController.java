package com.team3.groupware.nohyun.controller;



import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.team3.groupware.common.model.Criteria;
import com.team3.groupware.common.model.PageMaker;
import com.team3.groupware.eunji.model.BoardVO;
import com.team3.groupware.eunji.service.BoardService;

@Controller
public class BoardDocuController {
	
	@Inject
	BoardService boardService;
	
	// 자료실
	@GetMapping("/board_docu")
	public ModelAndView boardNoraml(BoardVO boardVo, HttpServletRequest request, Criteria cri) {
		
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		int emp_num = 0;
		
		
		if(session.getAttribute("emp_num") != null) {
			String change = String.valueOf(session.getAttribute("emp_num"));
			
			// 페이징
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(boardService.countTotal_boardDocuList());
			
			List<BoardVO> boardDocuList = boardService.board_selectDList(cri);
			mv.addObject("boardDocuList", boardDocuList);
			mv.addObject("pageMaker", pageMaker);
		}
		mv.setViewName("/nohyun/board/board_docu");
		return mv;
	}
	
		
	}

	
	

