package com.team3.groupware.jeongchi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.team3.groupware.common.model.EmployeeVO;
import com.team3.groupware.common.model.PageUtil;
import com.team3.groupware.common.service.EmployeeService;
import com.team3.groupware.jeongchi.model.MessageVO;
import com.team3.groupware.jeongchi.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@Autowired
	EmployeeService empService;
	
//	================ message_write.jsp 시작  ============== //
	
	@GetMapping("/message/write")
	public ModelAndView write() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jeongchi/message/message_write");
		return mv;
	}
	
//	=== 메시지 insert ===	
	@PostMapping("/message/sendMessage")
	public int sendMessage(MessageVO messageVO) {
		messageService.sendMessage(messageVO);		
		return 0;
	}

// === 쪽지함의 쪽지쓰기의 주소록에서 조직 클릭한 경우(ajax) === //
	@GetMapping("/message/writeAddress")
	public List<EmployeeVO> writeAddress(HttpServletRequest request) {
//		public List<EmployeeDTO> writeAddress(@RequestParam List<EmployeeDTO> empMap) {
		String dept_code = request.getParameter("dept_code");
		Map<String, Object> empMap = new HashMap<>();
		empMap.put("dept_code", dept_code);
		// 부서에 따른 사원조회
		List<EmployeeVO> empList = empService.writeAddress(empMap);
		
		return empList;
	}

//	=== 쪽지함의 쪽지쓰기의 주소록에서 검색한 경우(ajax) ===	
	@PostMapping("/message/writeAddressSearch")
	public List<EmployeeVO> writeAddressSearch(@RequestParam String search_option, 
			@RequestParam(defaultValue = "" ) String keyword) {
		
		Map<String, Object> addSearchMap = new HashMap<>();
		addSearchMap.put("search_option", search_option);
		addSearchMap.put("keyword", keyword);
		// 부서에 따른 사원조회
		List<EmployeeVO> addSearchList = messageService.writeAddressSearch(addSearchMap, search_option, keyword);
		return addSearchList;
	}
//	================ message_write.jsp 끝  ============== //
	
	
//	================ message_send.jsp 시작  ============== //
	@GetMapping("/message/message_send")
	public ModelAndView sendMessageList(
			@RequestParam(defaultValue = "1") int curPage,
			MessageVO messageVO, HttpServletRequest request) {	
		
		HttpSession session = request.getSession();
		String change = String.valueOf(session.getAttribute("emp_num"));
	    int emp_num = Integer.parseInt(change);
		int count = messageService.count(messageVO);
		
		PageUtil page_info = new PageUtil(count, curPage);
		int start = page_info.getPageBegin();
		int end = page_info.getPageEnd();
		
		List<MessageVO> sendMessageList = messageService.sendMessageList(start, end, messageVO, emp_num);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jeongchi/message/message_send");
		Map<String, Object> messageMap = new HashMap<>();
		
		messageMap.put("sendMessageList", sendMessageList);
		messageMap.put("count", count);
		messageMap.put("page_info", page_info);
		mv.addObject("messageMap", messageMap);
		//mv.addObject("list", list);
		return mv;
	}
//	================ message_send.jsp 끝  ============== //
//	================ message_receive.jsp 시작  ============== //
	
	@GetMapping("/message/message_receive")
	public ModelAndView receiveMessageList(
			@RequestParam(defaultValue = "1") int curPage,
			MessageVO messageVO, HttpServletRequest request) {	
		
		HttpSession session = request.getSession();
		String change = String.valueOf(session.getAttribute("emp_num"));
	    int emp_num = Integer.parseInt(change);
		int count = messageService.count(messageVO);
		
		PageUtil page_info = new PageUtil(count, curPage);
		int start = page_info.getPageBegin();
		int end = page_info.getPageEnd();
		
		List<MessageVO> receiveMessageList = messageService.receiveMessageList(start, end, messageVO, emp_num);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jeongchi/message/message_receive");
		Map<String, Object> messageMap2 = new HashMap<>();
		
		messageMap2.put("receiveMessageList", receiveMessageList);
		messageMap2.put("count", count);
		messageMap2.put("page_info", page_info);
		mv.addObject("messageMap", messageMap2);
		//mv.addObject("list", list);
		return mv;
	}
	
//	================ message_receive.jsp 끝  ============== //
//	================ message_important.jsp 시작  ============== //
	
	@GetMapping("/message/message_important")
	public ModelAndView importantMessageList(
			@RequestParam(defaultValue = "1") int curPage,
			MessageVO messageVO, HttpServletRequest request) {	
		
		HttpSession session = request.getSession();
		String change = String.valueOf(session.getAttribute("emp_num"));
		int emp_num = Integer.parseInt(change);
		int count = messageService.count(messageVO);
		
		PageUtil page_info = new PageUtil(count, curPage);
		int start = page_info.getPageBegin();
		int end = page_info.getPageEnd();
		
		List<MessageVO> importantMessageList = messageService.importantMessageList(start, end, messageVO, emp_num);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jeongchi/message/message_important");
		Map<String, Object> messageMap3 = new HashMap<>();
		
		messageMap3.put("importantMessageList", importantMessageList);
		messageMap3.put("count", count);
		messageMap3.put("page_info", page_info);
		mv.addObject("messageMap", messageMap3);
		//mv.addObject("list", list);
		return mv;
	}
	
//	================ message_important.jsp 끝  ============== //
//	================ message_temp.jsp 시작  ============== //
	
	@GetMapping("/message/message_temp")
	public ModelAndView tempMessageList(
			@RequestParam(defaultValue = "1") int curPage,
			MessageVO messageVO, HttpServletRequest request) {	
		
		HttpSession session = request.getSession();
		String change = String.valueOf(session.getAttribute("emp_num"));
		int emp_num = Integer.parseInt(change);
		int count = messageService.count(messageVO);
		
		PageUtil page_info = new PageUtil(count, curPage);
		int start = page_info.getPageBegin();
		int end = page_info.getPageEnd();
		
		List<MessageVO> tempMessageList = messageService.tempMessageList(start, end, messageVO, emp_num);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jeongchi/message/message_temp");
		Map<String, Object> messageMap4 = new HashMap<>();
		
		messageMap4.put("tempMessageList", tempMessageList);
		messageMap4.put("count", count);
		messageMap4.put("page_info", page_info);
		mv.addObject("messageMap", messageMap4);
		//mv.addObject("list", list);
		return mv;
	}
	
//	================ message_temp.jsp 끝  ============== //
//	================ message_detail.jsp 시작  ============== //
	
	@GetMapping("/message/message_detail")
	public ModelAndView message_detail(MessageVO messageVO) {
		Map<String, Object> messageDetailMap = messageService.message_detail(messageVO);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jeongchi/message/message_detail");
		mv.addObject("data", messageDetailMap);
		return mv;
	}
	
//	================ message_detail.jsp 끝  ============== //

}



