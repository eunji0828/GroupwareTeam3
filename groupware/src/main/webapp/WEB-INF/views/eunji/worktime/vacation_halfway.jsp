<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../resources/css/worktime/worktime_new.css">

</head>
<table class="input_table">
                        <tr class="input_table_size">
                            <td>반차신청서</td>
                            <td colspan="6"></td>
                        </tr>
                        <tr>
                            <td>기안부서</td>
                            <td class="table_size">${WorktimeNameMap.dept_name}</td>
                            <td>기안일</td>
                            <td class="table_size worktime_new_date"></td>
                            <td class="docu_num_width">부서번호</td>
                            <td class="table_size">${WorktimeNameMap.dept_code}</td>
                        </tr>

                  		<tr>
                     		<td>휴가 기간</td>
                            <td colspan="2" class="date_width">
                            	<div class="vacation_date"><input type="date" class="vacation_days1"></div>
                            </td>
                    		<td>오전/오후</td>
                            <td colspan="2">
                            	<label for="halfway_morning"><input type="radio" name="halfway" id="halfway_morning" checked>오전</label> &nbsp
                            	<label for="halfway_after"><input type="radio" name="halfway" id="halfway_after">오후</label>
                            </td>
                  		</tr>   
                  		<tr>
                            <td>사유</td>
                            <td colspan=6"><input type="text"  id="worktime_docu_reason"></td>
                        </tr>
                      	<tr>
                           <td>제목</td>
                           <td colspan="6"><input type="text" id="worktime_docu_title"></td>
                        </tr>
                        <tr>
                        	<td>첨부 파일</td>
                        	<td colspan="6"><input type="file" id="file_text_align"></td>
                        </tr>
                    </table>

</html>