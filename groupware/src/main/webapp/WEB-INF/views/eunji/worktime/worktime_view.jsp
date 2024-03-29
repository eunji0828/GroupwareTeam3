<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>JaeHee Group</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="../../resources/css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="../../resources/css/worktime/worktime_view.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
<!-- 상단/왼쪽 메뉴 include -->
<%@ include file="/WEB-INF/views/intro.jsp" %>

        <div id="layoutSidenav_content">
            <div class="content_box">
                	출퇴근현황
            </div>
            

            <!-- 출퇴근현황 리스트 테이블 -->
            <div class="worktime_view_list">
                <p>내 출퇴근 내역 
                 <select id='worktime_month_select' name="worktime_month_type">
                      <option class="worktime_month_option" value='Jan'>1월</option>
                      <option class="worktime_month_option" value='Fed'>2월</option>
                      <option class="worktime_month_option" value='Mar'>3월</option>
                      <option class="worktime_month_option" value='Apr'>4월</option>
                      <option class="worktime_month_option" value='May'>5월</option>
                      <option class="worktime_month_option" value='Jun'>6월</option>
                      <option class="worktime_month_option" value='Jul'>7월</option>
                      <option class="worktime_month_option" value='Aug'>8월</option>
                      <option class="worktime_month_option" value='Sept'>9월</option>
                      <option class="worktime_month_option" value='Oct'>10월</option>
                      <option class="worktime_month_option" value='Nov'>11월</option>
                      <option class="worktime_month_option" value='Dec'>12월</option>
                 </select></p>
                <div class="worktime_view_table_list">
                <table class="worktime_view_table">
                    <tr class="worktime_view_table_bg">
                        <td>업무일자</td>
                        <td>출근시간</td>
                        <td>퇴근시간</td>
                        <td>총근무시간</td>
                    </tr>
                      <c:forEach var="view" items="${worktime_view_list}">
                    <tr class="worktime_view_table_hover">
                        <td><fmt:formatDate value="${view.workdate}"
                           pattern="yy-MM-dd" /></td>
                        <td>${view.worktime_go}</td>
                        <td>${view.worktime_out}</td>
                        <td>${view.worktime_hours}</td>
                    </tr>
                    </c:forEach>
                </table>
                </div>

        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <script src="<c:url value='resources/js/officemap.js'/>"></script>
   
</body>

</html>