package com.team3.groupware.seulgi.controller;

import com.team3.groupware.common.model.EmployeeVO;
import com.team3.groupware.common.service.EmployeeService;
import com.team3.groupware.seulgi.service.AdminService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AdminService adminService;




    //===========================================================인사기록카드
    //인사기록카드 페이지 view
    @GetMapping ("/admin/admin_personnelCard")
    public ModelAndView personnelCard() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/seulgi/admin/admin_personnelCard");
        return mv;
    }

    //사원정보 검색
    @PostMapping("/admin/admin_personnelCard/search")
    public ModelAndView personnelCardSearch(@RequestBody Map<String, Object> map) {
        ModelAndView mv = new ModelAndView();
//        List<EmployeeVO> vv = adminService.personnelCardSearch(map);
//        for(int i = 0; i < vv.size(); i++){
//            System.out.println(vv.get(i).getLeave_date());
//        }

//        System.out.println(adminService.personnelCardSearch(map));
        mv.addObject("data", adminService.personnelCardSearch(map));
        mv.setViewName("/seulgi/admin/admin_personnelCard_searchList");

        return mv;
    }

    //사원정보 수정
    @PostMapping("/admin/admin_personnelCard/modify")
    @ResponseBody
    public ModelAndView personnelCardModify(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Map<String, Object> map) {
        String cast = (String) map.get("init_check");
        int init_check = Integer.parseInt(cast);
        if(init_check == 0){
            adminService.initPW(map);
        }
        adminService.modify(map);
        adminService.personnelCardSearch(map);

        ModelAndView mv = new ModelAndView();
        mv.addObject("data", adminService.personnelCardSearch2(map));
        mv.setViewName("/seulgi/admin/admin_personnelCard_searchList");
        System.out.println(adminService.personnelCardSearch2(map));
        return mv;
    }

    //사원정보 등록
    //사원정보 등록페이지 출력
    @GetMapping ("/admin/admin_personnelCardRegister")
    public ModelAndView personnelCardRegister() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/seulgi/admin/admin_personnelCardRegister");
        return mv;
    }

    //사원정보 등록
    @PostMapping("/admin/admin_personnelCardRegister")
    @ResponseBody
    public void personnelCardRegister(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Map<String, Object> map) {
        adminService.insert(map);
    }

    //사원정보 삭제
    @PostMapping("/admin/admin_personnelCard/delete")
    @ResponseBody
    public ModelAndView personnelCardDelete(@RequestParam Map<String, Object> map) {

        adminService.delete(map);

        ModelAndView mv = new ModelAndView();
        mv.addObject("data", adminService.personnelCardSearch2(map));
        mv.setViewName("/seulgi/admin/admin_personnelCard_searchList");

        return mv;
    }




    //===========================================================접속내역조회
    //접속내역조회 페이지 출력
    @GetMapping("/admin/admin_loginHistory")
    public ModelAndView loginHistory() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/seulgi/admin/admin_loginHistory");
        return mv;
    }

    //접속내역조회 검색
    @PostMapping("/admin/admin_loginHistory/search")
    public ModelAndView loginHistorySearch(@RequestBody Map<String, Object> map) {

        ModelAndView mv = new ModelAndView();

        mv.addObject("data", adminService.loginHistorySearch(map));
        mv.setViewName("/seulgi/admin/admin_loginHistory_searchList");

        return mv;
    }

    //접속내역 엑셀 출력
    @RequestMapping(value="/excelDown.do")
    public void excelDown(HttpServletResponse response, @ModelAttribute("ExcelDown")EmployeeVO ExcelDown) throws IOException {

        //엑셀요소 생성
        XSSFWorkbook wb = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        wb = new XSSFWorkbook();
        sheet = wb.createSheet("접속내역확인");

        //게시판 목록조회
        List<EmployeeVO> excelList = this.adminService.excelList(ExcelDown);

        //헤더 생성
        int cellCount = 0;
        row = sheet.createRow(0); //0번째 행
        cell = row.createCell(cellCount++);
        cell.setCellValue("No.");
        cell = row.createCell(cellCount++);
        cell.setCellValue("사원번호");
        cell = row.createCell(cellCount++);
        cell.setCellValue("사원명");
        cell = row.createCell(cellCount++);
        cell.setCellValue("접속일시");

        //데이터 부분 생성
        for(int i=0; i<excelList.size(); i++) {
            row = sheet.createRow(i+1); //'열 이름 표기'로 0번째 행 만들었으니까 1번째 행부터
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+512 );
            cellCount = 0; //열 번호 초기화
            cell = row.createCell(cellCount++);
            cell.setCellValue(i+1);
            cell = row.createCell(cellCount++);
            cell.setCellValue(excelList.get(i).getEmp_num());
            cell = row.createCell(cellCount++);
            cell.setCellValue(excelList.get(i).getEmp_name());
            cell = row.createCell(cellCount++);
            cell.setCellValue(new Date());

            //접속일시가 날짜형식이라 타입포맷 후 받아오기
            CellStyle cellStyle = wb.createCellStyle();
            CreationHelper createHelper = wb.getCreationHelper();
            cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

            cell.setCellStyle(cellStyle);
            cell.setCellValue(excelList.get(i).getRecent_datetime());
        }

        //컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=loginHistory.xlsx");

        //엑셀 출력
        wb.write(response.getOutputStream());
    }




    //===========================================================권한관리
    //권한관리 페이지 출력
    @GetMapping("/admin/admin_authority")
    public ModelAndView authority() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/seulgi/admin/admin_authority");
        return mv;
    }

    //권한 검색
    @PostMapping("/admin/admin_authority/search")
    public ModelAndView authoritySearch(@RequestBody Map<String, Object> map) {

        System.out.println(map);

        ModelAndView mv = new ModelAndView();

        mv.addObject("data", adminService.authoritySearch(map));
        mv.setViewName("/seulgi/admin/admin_authority_searchList");

        System.out.println(adminService.authoritySearch(map));

        return mv;
    }

    //권한 수정
    @PostMapping("/admin/admin_authority/modify")
    @ResponseBody
    public void adminAuthorityModify(HttpServletRequest request){
        String[] authority_array = request.getParameterValues("authority_array");
        Map<String,Object> map = new HashMap<>();
        map.put("emp_num",Integer.parseInt(authority_array[0]));
        map.put("authority_EDMS",Integer.parseInt(authority_array[1]));
        map.put("authority_worktime",Integer.parseInt(authority_array[2]));
        map.put("authority_booking",Integer.parseInt(authority_array[3]));
        map.put("authority_board",Integer.parseInt(authority_array[4]));
        map.put("authority_personnelCard",Integer.parseInt(authority_array[5]));
        map.put("authority_loginHistory",Integer.parseInt(authority_array[6]));
        map.put("authority_authority",Integer.parseInt(authority_array[7]));
        adminService.adminAuthorityModify(map);
    }


    //===========================================================근태관리
    //근태관리 페이지 출력(출퇴근시간관리)
    @GetMapping ("/admin/admin_worktime")
    public ModelAndView worktime() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/seulgi/admin/admin_worktime");
        return mv;
    }




    //근태관리 페이지 출력(연차일수관리)
    @GetMapping ("/admin/admin_worktimeVacation")
    public ModelAndView worktimeVacation() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/seulgi/admin/admin_worktimeVacation");
        return mv;
    }


}
